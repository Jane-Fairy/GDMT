package com.component;

import com.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void orderComplete(Order order){
        rabbitTemplate.convertAndSend("order","complete",order);
    }
}
