package com.component;

import com.entity.Contract;
import com.service.OrderTradeService;
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
    private OrderTradeService orderTradeService;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "contract"),
                    exchange = @Exchange(value = "contract",type = ExchangeTypes.DIRECT),
                    key = "contract"
            )
    })
    public void orderComplete(Contract contract){
        orderTradeService.createOrder(contract);
    }
}
