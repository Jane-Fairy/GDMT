package com.controller;

import com.entity.Contract;
import com.entity.ResultBean;
import com.service.OrderTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderTradeController {

    @Autowired
    private OrderTradeService orderTradeService;

    /**
     * 生成订单
     * @param contract
     * @return
     */
    @RequestMapping("/create")
    public ResultBean createOrder(@RequestBody Contract contract){
        return orderTradeService.createOrder(contract);
    }

    /**
     * 买方付款
     * @param orderId
     * @param request
     * @return
     */
    @RequestMapping("/buyPay/{orderId}")
    public ResultBean buyPay(@PathVariable Integer orderId, HttpServletRequest request){
        return orderTradeService.buyPay(orderId,request);
    }

    /**
     * 查找所有订单
     * 管理员接口
     * @return
     */
    @RequestMapping("/findBy/all/{page}")
    public ResultBean findAllOrder(@PathVariable int page){
        return orderTradeService.findAllOrder(page);
    }

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    @RequestMapping("/findBy/{orderId}")
    public ResultBean findOrderById(@PathVariable Integer orderId){
        return orderTradeService.findOrderById(orderId);
    }

    /**
     * 根据用户id查询订单
     * @param userId
     * @return
     */
    @RequestMapping("/findBy/{userId}/{page}")
    public ResultBean findOrderByUser(@PathVariable Integer userId,@PathVariable int page){
        return orderTradeService.findOrderByUser(page,userId);
    }

    /**
     * 更新订单状态
     * 管理员接口
     * @param orderId
     * @param orderState
     * @return
     */
    @RequestMapping("/updateState/{orderId}/{orderState}")
    public ResultBean updateOrderState(@PathVariable Integer orderId, @PathVariable Integer orderState){
        return orderTradeService.updateOrderState(orderId,orderState);
    }

    /**
     * 卖家发货
     * @param orderId
     * @return
     */
    @RequestMapping("/sellSend/{orderId}")
    public ResultBean sellSend(@PathVariable Integer orderId){
        return orderTradeService.sellSend(orderId);
    }

    /**
     * 订单签收
     * @param orderId
     * @return
     */
    @RequestMapping("/complete/{orderId}")
    public ResultBean orderComplete(@PathVariable Integer orderId){
        return orderTradeService.orderComplete(orderId);
    }

    /**
     *查询订单详情
     * @param buy   买方用户名
     * @param sell  卖方用户名
     * @param contractId    订单绑定的合同id
     * @return
     */
    @RequestMapping("/orderFull/{buy}/{sell}/{contractId}")
    public ResultBean orderFul(@PathVariable String buy,@PathVariable String sell ,@PathVariable Integer contractId){
        return orderTradeService.orderFull(buy,sell,contractId);
    }
}
