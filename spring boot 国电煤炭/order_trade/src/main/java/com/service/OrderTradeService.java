package com.service;

import com.entity.Contract;
import com.entity.ResultBean;
import javax.servlet.http.HttpServletRequest;

public interface OrderTradeService {

    ResultBean findAllOrder(int page);

    ResultBean createOrder(Contract contract);

    ResultBean buyPay(Integer orderId,HttpServletRequest request);

    ResultBean findOrderById(Integer orderId);

    ResultBean findOrderByUser(int page,Integer userId);

    ResultBean updateOrderState(Integer orderId,Integer orderState);

    ResultBean orderComplete(Integer orderId);

    ResultBean sellSend(Integer orderId);

    ResultBean orderFull(String buy,String sell,Integer contractId);

}
