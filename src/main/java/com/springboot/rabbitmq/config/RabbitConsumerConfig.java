package com.springboot.rabbitmq.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/*
 * 消费者配置
 */
@Configuration
public class RabbitConsumerConfig {
    private static String EXCHANGE_NAME="SFF_EXCHANGE";//交换机名称
    private static String QUEUE_NAME="SFF_QUEUE";//队列名称

    /*
    声明交换机
     boolean durable 是否持久化
     boolean autoDelete 是否自动删除
    * */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE_NAME,true,false);
    }

    /*
    声明队列
     exclusive:是否独占，用于确保只有一个消费者可以访问该队列。
     当某个消费者连接到该队列并开始消费消息时，其他消费者将无法访问该队列
     */
    @Bean
    public Queue message(){
        return new Queue(QUEUE_NAME,true,false,false);
    }

    /*
      声明绑定关系:将队列绑定到交换机上
     */
    @Bean
    public Binding queueBinding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("product.*");
    }

}
