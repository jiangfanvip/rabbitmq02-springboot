package com.jiangfan.rabbitmq02springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 江帆
 * @Date: 2020/7/18
 * @Description: com.jiangfan.rabbitmq02springboot.config
 * @version: 1.0
 */
@Configuration
public class RabbitMqConfig {
    //交换机名称
    public static final String exchange_name = "springboot_exchange";
    //队列名称
    public static final String queue_name = "springboot_queue";

    /**
     * 功能描述:声明交换机
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Bean("itemExchange")
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange(exchange_name).durable(true).build();

    }

    /**
     * 功能描述: 声明队列
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Bean("itemQueue")
    public Queue declareQueue() {

        return QueueBuilder.durable(queue_name).build();
    }

   /**
   *  功能描述: 用生成的队列和交换机 实例进行绑定,使用
   * @Auther:  江帆
   * @Date:
   * @Description:
   */
    @Bean
    public Binding bindingExchange(@Qualifier("itemQueue") Queue queue, @Qualifier("itemExchange") Exchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with("item.#").noargs();

    }
}
