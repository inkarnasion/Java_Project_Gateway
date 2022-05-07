package com.example.java_project_gateway.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

// @Service
@RabbitListener(queues = "Gateway-Statistics-Queue")
public class RabbitMqReceiver {
    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }
}
