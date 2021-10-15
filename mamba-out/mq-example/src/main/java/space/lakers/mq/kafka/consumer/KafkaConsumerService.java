package space.lakers.mq.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
 * @author mini kobe
 * @date 2019/8/24
 * <pre>
 *      消费者
 *
 *      可以通　canal 发MQ ，注意topic的名字
 * </pre>
 */
@Slf4j
@EnableBinding(ConsumerSink.class)
@Component
public class KafkaConsumerService {

    @StreamListener(ConsumerSink.INPUT)
    public void listen(Message<?> message) {
        log.info("message Received: {}", message.getPayload());
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            log.info("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }

}
