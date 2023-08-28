package com.springboot.rabbitmq.listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleConsumer {
    //消费者监听队列 处理消息
    @RabbitListener(queues = "SFF_QUEUE")
    public void subscribe(Message message){
       log.info("收到消息:{}",message);
        byte[] body =message.getBody();
        System.out.println("收到消息:"+new String(body));
    }
}
