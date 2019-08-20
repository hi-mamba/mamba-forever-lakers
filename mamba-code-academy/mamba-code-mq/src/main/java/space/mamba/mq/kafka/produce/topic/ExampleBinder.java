package space.mamba.mq.kafka.produce.topic;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author pankui
 * @date 2019-08-19
 * <pre>
 *
 * </pre>
 */
public interface ExampleBinder {

    String NAME = "kafkatopic";

    @Input(NAME)
    SubscribableChannel input();
}
