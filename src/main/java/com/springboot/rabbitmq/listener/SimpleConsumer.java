package com.springboot.rabbitmq.listener;
import com.alibaba.fastjson.JSON;
import com.springboot.pojo.Grade;
import com.springboot.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class SimpleConsumer {
    //消费者监听队列 处理消息
    @Autowired
    private GradeService gradeService;
    @RabbitListener(queues = "SFF_QUEUE")
    public void subscribe(@Payload String message){
       log.info("收到消息:{}", message);
        try {
            Grade grade = JSON.parseObject(message, Grade.class);
            gradeService.saveGrade(grade);
            log.info("消费成功!");
        } catch (Exception e) {
            log.info("消费失败!"+e);
        }
    }
}
