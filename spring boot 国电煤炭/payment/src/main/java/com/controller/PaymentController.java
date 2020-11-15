package com.controller;

import com.entity.ResultBean;
import com.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * 冻结用户资金
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/freeze/{userId}/{money}")
    public ResultBean freeze(@PathVariable Integer userId,@PathVariable Double money){
        return paymentService.toFreeze(userId,money);
    }

    /**
     * 恢复冻结
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/account/{userId}/{money}")
    public ResultBean account(@PathVariable Integer userId,@PathVariable Double money){
       return paymentService.toAccount(userId,money);
    }

    /**
     * 扣款
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/def/{userId}/{money}")
    public ResultBean def(@PathVariable Integer userId,@PathVariable Double money){
       return paymentService.def(userId,money);
    }

    /**
     * 到账
     * @param userId
     * @param money
     * @return
     */
    @RequestMapping("/add/{userId}/{money}")
    public ResultBean add(@PathVariable Integer userId,@PathVariable Double money){
        return paymentService.add(userId,money);
    }
}
