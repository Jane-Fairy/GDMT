package com.service;

import com.entity.ResultBean;
import org.apache.ibatis.annotations.Select;

public interface PaymentService {

    //冻结
    ResultBean toFreeze(Integer userId, Double money);

    //取消冻结
    ResultBean toAccount(Integer userId,Double money);

    //扣款
    ResultBean def(Integer userId,Double money);

    //到账
    ResultBean add(Integer userId,Double money);

    //创建账户
    ResultBean create(Integer userId);

    //查询余额
    ResultBean find(Integer userId);

    //查询冻结
    ResultBean findFreeze(Integer userId);
}
