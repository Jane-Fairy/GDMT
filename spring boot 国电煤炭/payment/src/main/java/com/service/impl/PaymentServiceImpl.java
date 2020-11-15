package com.service.impl;

import com.dao.PaymentDao;
import com.entity.ResultBean;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public ResultBean toFreeze(Integer userId, Double money) {
        if (paymentDao.find(userId)>=money){
            try {
                paymentDao.toFreeze(userId,money);
                return new ResultBean(200,"冻结成功",money);
            }catch (Exception e){
                return new ResultBean(400,"冻结异常");
            }
        }
        return new ResultBean(400,"余额不足");
    }

    @Override
    public ResultBean toAccount(Integer userId, Double money) {
        if (paymentDao.findFreeze(userId)>=money){
            try {
                paymentDao.toAccount(userId,money);
                return new ResultBean(200,"恢复成功",money);
            }catch (Exception e){
                return new ResultBean(400,"恢复异常");
            }
        }
        return new ResultBean(400,"冻结资金不足");
    }

    @Override
    public ResultBean def(Integer userId, Double money) {
        if (paymentDao.findFreeze(userId)>=money){
            try {
                paymentDao.def(userId,money);
                return new ResultBean(200,"扣款成功",money);
            }catch (Exception e){
                return new ResultBean(400,"扣款异常");
            }
        }
        return new ResultBean(400,"扣款异常");
    }

    @Override
    public ResultBean add(Integer userId, Double money) {
        try {
            paymentDao.add(userId,money);
            return new ResultBean(200,"到账",money);
        }catch (Exception e){
            return new ResultBean(400,"异常");
        }
    }

    @Override
    public ResultBean create(Integer userId) {
       return null;
    }

    @Override
    public ResultBean find(Integer userId) {
        return null;
    }

    @Override
    public ResultBean findFreeze(Integer userId) {
        return null;
    }
}
