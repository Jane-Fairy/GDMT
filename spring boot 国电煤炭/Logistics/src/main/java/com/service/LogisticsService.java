package com.service;

import com.entity.Order;
import com.entity.ResultBean;
import com.entity.Torder;

import java.util.List;

/**
 * @author 寰
 * @create 2020-11-04-16:24
 */
public interface LogisticsService {

    int findGoodsQuantity(Integer goodsId);

    List<Torder> findAllByState(Integer page,Integer pageSize,Integer torderState, Integer userId);

    ResultBean createTorder(Order order,Integer userId,String phone);
}
