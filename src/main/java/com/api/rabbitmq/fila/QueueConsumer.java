package com.api.rabbitmq.fila;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"}, ackMode = "MANUAL")
    public void receive(@Payload Message message) throws BusinessException {
        System.out.println("Message " + message + "  " + LocalDateTime.now());
        String ultima = String.valueOf(message.getHeaders().get("ultima"));
        if(ultima.equals("sim")){
            System.out.println(ultima);
        }
        String payload = String.valueOf(message.getPayload());
        if(payload.equals(1)) {
            throw new BusinessException("testando a excecao");
        }
    }
}
