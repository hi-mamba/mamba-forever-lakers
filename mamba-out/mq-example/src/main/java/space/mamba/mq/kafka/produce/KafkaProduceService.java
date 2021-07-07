package space.mamba.mq.kafka.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import space.mamba.mq.kafka.produce.topic.ProducerSource;

/**
 * @author pankui
 * @date 2019-08-19
 * <pre>
 *      @EnableBinding注解在你的程序中，被@StreamListener修饰的方法可以立即连接到消息代理接收流处理事件。
 *
 *      添加@EnableBinding注解到你的应用中来快速连接到消息代理，添加@StreamListener注解到一个方法上，
 *      这个方法会接收到Stream处理事件。
 *
 * </pre>
 */
@EnableBinding(ProducerSource.class)
public class KafkaProduceService {

    @Autowired
    private ProducerSource producerSource;


    public void sendMessage(String msg) {
        try {
            producerSource.topicOutput().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
