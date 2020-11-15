package com.service.impl;

import com.component.RabbitProvider;
import com.dao.OrderTradeDao;
import com.entity.Contract;
import com.entity.Goods;
import com.entity.Order;
import com.entity.ResultBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.OrderTradeService;
import com.utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderTradeServiceImpl implements OrderTradeService {

    @Autowired
    private OrderTradeDao orderTradeDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitProvider rabbitProvider;

    /**
     * 查询所有订单
     * @return
     */
    @Override
    public ResultBean findAllOrder(int page) {
        try {
            PageHelper.startPage(page,15,"order_time desc");
            List<Order> orderList=orderTradeDao.findAllOrder();
            PageInfo pageInfo=new PageInfo(orderList);
            return new ResultBean(200,"查询所有订单",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"查询异常");
        }
    }

    /**
     * 订单生成
     * @param contract 合同对象
     * @return
     */
    @Override
    public ResultBean createOrder(Contract contract) {
        try {
            Date date=new Date();
            Order order=new Order();
            order.setOrderNumber(date+contract.getGoods().getGoodsType()+date.getDate());
            order.setOrderTime(date);
            order.setContractId(contract.getContractId());
            order.setMoney(contract.getGoods().getGoodsPrice());
            //卖方挂牌
            if (contract.getGoods().getInfoType()==0){
                order.setSellFreeze(contract.getGoods().getUserFreeze());
                order.setBuyFreeze(0d);
            }
            //买方挂牌
            if (contract.getGoods().getInfoType()==1){
                order.setSellFreeze(0d);
                order.setBuyFreeze(contract.getGoods().getUserFreeze());
            }
            orderTradeDao.createOrder(order);
            orderTradeDao.addUserOrder(contract.getSellId(),contract.getBuyId(),order.getOrderId());
            return new ResultBean(200,"生成订单",order);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"订单生成异常");
        }
    }

    /**
     * 买方付款
     * @param orderId 订单id
     * @param request
     * @return
     */
    @Override
    public ResultBean buyPay(Integer orderId,HttpServletRequest request) {
        try {
            Order order=orderTradeDao.findOrderById(orderId);
            ResultBean resultBean=restTemplate.postForObject("http://localhost:9005/payment/freeze/"+order.getBuy().getUserId()+"/"+order.getMoney(),HeaderUtils.getHeader(request),ResultBean.class);
            if (resultBean.getCode()==200){
                orderTradeDao.buyFreezeUpdate(orderId,order.getMoney());
                orderTradeDao.updateBuyState(orderId,1);
            }
            return resultBean;
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"付款异常");
        }
    }

    /**
     * 根据订单id查找订单
     * @param orderId 订单id
     * @return
     */
    @Override
    public ResultBean findOrderById(Integer orderId) {
        try {
            return new ResultBean(200,"订单查询成功",orderTradeDao.findOrderById(orderId));
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"订单查询异常");
        }
    }

    /**
     * 根据用户id查找订单
     * @param userId
     * @return
     */
    @Override
    public ResultBean findOrderByUser(int page ,Integer userId) {
        try {
            PageHelper.startPage(page,15,"order_time desc");
            List<Order> orderList=orderTradeDao.findOrderByUser(userId);
            PageInfo pageInfo=new PageInfo(orderList);
            return new ResultBean(200,"订单查询成功",pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"订单查询异常");
        }
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderState
     * @return
     */
    @Override
    public ResultBean updateOrderState(Integer orderId, Integer orderState) {
        try {
            if (orderId>=0&&orderId<4){
                int flag=orderTradeDao.updateOrderState(orderId,orderState);
                if (flag>0){
                    return new ResultBean(200,"订单状态更新");
                }
                return new ResultBean(400,"订单不存在");
            }
            return new ResultBean(400,"状态参数错误");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"订单状态更新异常");
        }
    }

    /**
     * 订单签收
     * @param orderId
     * @return
     */
    @Override
    public ResultBean orderComplete(Integer orderId) {
        try {
            int flag=orderTradeDao.orderComplete(orderId);
            if (flag>0){
                Order order=orderTradeDao.findOrderById(orderId);
                if (order!=null){
                    rabbitProvider.orderComplete(order);
                    return new ResultBean(200,"订单签收成功");
                }
            }
            return new ResultBean(400,"订单不存在");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"订单签收异常");
        }
    }

    /**
     * 卖家发货
     * @param orderId
     * @return
     */
    @Override
    public ResultBean sellSend(Integer orderId) {
        try {
            int flag=orderTradeDao.sellSend(orderId);
            if (flag>0){
                return new ResultBean(200,"发货成功");
            }
            return new ResultBean(400,"订单不存在");
        }catch (Exception e){
            e.printStackTrace();
            return new ResultBean(400,"发货异常");
        }
    }

    /**
     * 查看订单详情
     * @param buy
     * @param sell
     * @param contractId
     * @return
     */
    @Override
    public ResultBean orderFull(String buy, String sell, Integer contractId) {
        try {
            Map<String,Object> map=new HashMap<>();
            map.put("buy",buy);
            map.put("sell",sell);
            map.put("goods",orderTradeDao.findGoods(contractId));
            return new ResultBean(200,"详情查询成功",map);
        }catch (Exception e){
            return new ResultBean(400,"详情查询异常");
        }
    }

    /**
     * 过期闲置1星期的订单
     */
    @Scheduled(cron = "0 0 * * 2 ?")
    public void overTime(){
        Date date=new Date();
        List<Order> orderList= orderTradeDao.findAllOrder();
        for (Order order:orderList){
            if (order.getOrderState()==0&&!(order.getSellState()==1&&order.getBuyState()==1)){
                if (date.getDay()-order.getOrderTime().getDay()==-1){
                    orderTradeDao.updateOrderState(order.getOrderId(),1);
                }
            }
        }
    }

    /**
     * 删除完成1个月的订单
     */
    @Scheduled(cron = "0 0 0 1 * ?")//每月1日0:00触发
    public void delOrder() {
        Date date=new Date();
        List<Order> orderList= orderTradeDao.findAllOrder();
        for (Order order:orderList){
            if (order.getOrderState()==3){
                if (Math.abs((date.getMonth()-order.getOrderTime().getMonth()) )>=1){
                    orderTradeDao.delUserOrder(order.getOrderId());
                    orderTradeDao.delOrder(order.getOrderId());
                }
            }
        }
    }

}
