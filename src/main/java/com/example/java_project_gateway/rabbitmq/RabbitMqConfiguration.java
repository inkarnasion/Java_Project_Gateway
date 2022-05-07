package com.example.java_project_gateway.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    @Bean
    public Queue getQueue() {
        return new Queue("Gateway-Statistics-Queue");
    }
}