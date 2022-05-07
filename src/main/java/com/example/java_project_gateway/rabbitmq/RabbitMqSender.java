package com.example.java_project_gateway.rabbitmq;

import com.example.java_project_gateway.entity.RequestsStatistic;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMqSender {

    private RabbitTemplate template;

    private Queue queue;

    public void send(RequestsStatistic requestsStatistic) {
        String message = requestsStatistic.toString();
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
