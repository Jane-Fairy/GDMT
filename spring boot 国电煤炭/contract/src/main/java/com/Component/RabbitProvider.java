package com.Component;

import com.entity.Contract;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void toOrder(Contract contract){
        rabbitTemplate.convertAndSend("contract","contract",contract);
    }
}
