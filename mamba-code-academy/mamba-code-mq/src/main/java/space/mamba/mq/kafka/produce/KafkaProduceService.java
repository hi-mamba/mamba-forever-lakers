package space.mamba.mq.kafka.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import space.mamba.mq.kafka.produce.topic.ExampleBinder;

/**
 * @author pankui
 * @date 2019-08-19
 * <pre>
 *
 * </pre>
 */
@EnableBinding(ExampleBinder.class)
public class KafkaProduceService {

    @Autowired
    private ExampleBinder exampleBinder;

  //  @StreamListener(ExampleBinder.NAME)
    public void sendMessage(String msg) {
        try {
            exampleBinder.input().send(MessageBuilder.withPayload(msg).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
