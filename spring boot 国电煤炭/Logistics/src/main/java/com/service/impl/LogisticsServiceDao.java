package com.service.impl;

import com.dao.LogisticsDao;
import com.entity.Order;
import com.entity.ResultBean;
import com.entity.Torder;
import com.github.pagehelper.PageHelper;
import com.service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.SecurityConstants;
import sun.security.util.SecurityProviderConstants;

import java.util.List;

/**
 * @author 寰
 * @create 2020-11-04-16:25
 */
@Service
@Transactional
public class LogisticsServiceDao implements LogisticsService {

    @Autowired
    private LogisticsDao logisticsDao;

    @Override
    public int findGoodsQuantity(Integer goodsId) {
        return logisticsDao.findGoodsQuantity(goodsId);
    }

    @Override
    public List<Torder> findAllByState(Integer page,Integer pageSize,Integer torderState, Integer userId) {
        //参数pageNum是页码值  参数pageSize代表的是每页显示条数
        PageHelper.startPage(page,pageSize);
        return logisticsDao.findAllByState(torderState,userId);
    }

    @Override
    public ResultBean createTorder(Order order,Integer userId, String phone) {
        try {
            Torder torder=new Torder();
            torder.setSellerId(order.getSell().getUserId());
            torder.setUserId(userId);
            torder.setPhone(phone);
            logisticsDao.createTorder(torder);
            logisticsDao.updateOrderTorderId(order.getOrderId(),torder.getTorderId());
            return new ResultBean(200,"物流订单创建成功");
        }catch (Exception e){
            return new ResultBean(400,"物流订单创建异常");
        }

    }
}
