package com.component;

import com.entity.Account;
import com.entity.Order;
import com.service.PaymentService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    @Autowired
    private PaymentService paymentService;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "msg"),
                    exchange = @Exchange(value = "order",type = ExchangeTypes.DIRECT),
                    key = "complete"
            )
    })
    public void orderComplete(Order order){
        //扣款，到账
        paymentService.def(order.getBuy().getUserId(),order.getMoney());
        paymentService.add(order.getSell().getUserId(),order.getMoney());
        //退还保证金
        paymentService.toAccount(order.getBuy().getUserId(),order.getBuyFreeze()-order.getMoney());
        paymentService.toAccount(order.getSell().getUserId(),order.getSellFreeze());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "account.info"),
                    exchange = @Exchange(value = "account",type = ExchangeTypes.DIRECT),
                    key = "account.info"
            )
    })
    public void creat(Account account){
        System.out.println(account);
        paymentService.create(account.getUserId());
    }
}
