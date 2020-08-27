package com.jiangfan.rabbitmq02springboot;

import com.jiangfan.rabbitmq02springboot.config.RabbitMqConfig;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Rabbitmq02SpringbootApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 功能描述: sringboot 来发送消息
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Test
    public void test01() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.exchange_name, "item.insert", "江帆--springboot-购买-msg");
    }

    /**
     * 功能描述:设置队列的时效性TTL
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Test
    public void test02() {

        rabbitTemplate.convertAndSend("my_ttl_queue", "一个超时的队列,15秒被删除");
    }

    /**
     * 功能描述:设置消息的时效性TTl
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Test
    public void test03() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("5000");
        Message message = new Message("一个超时的队列,5秒被删除".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("my_ttl_queue", message);
    }

    /**
     * 功能描述:测试死信队列  多久没被消费就会转发
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Test
    public void test04() {

        rabbitTemplate.convertAndSend("my_normal_exchange", "my_ttl_dlx", "一个死信队列6秒没有被消费就会被转发");
    }

    /**
     * 功能描述:测试死信队列   队列已经满了 则转发
     *
     * @Auther: 江帆
     * @Date:
     * @Description:
     */
    @Test
    public void test05() {
        for (int i = 0; i < 3; i++) {
            rabbitTemplate.convertAndSend("my_normal_exchange", "my_max_dlx", "一个死信队列" + i + "费就会被转发");

        }
    }
}
