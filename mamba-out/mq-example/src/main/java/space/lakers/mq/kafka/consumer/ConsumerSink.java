package space.lakers.mq.kafka.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author pankui
 * @date 2019/8/24
 * <pre>
 *
 *  http://blog.didispace.com/spring-cloud-starter-finchley-7-1/
 *
 *  在F版的Spring Cloud Stream中，当我们使用@Output和@Input注解来定义消息通道时，
 *  都会根据传入的通道名称来创建一个Bean。而在上面的例子中，我们定义的@Output和@Input名称是相同的，
 *  因为我们系统输入和输出是同一个Topic，这样才能实现对自己生产消息的消费。
 *
 *   既然这样，我们定义相同的通道名是行不通了，那么我们只能通过定义不同的通道名，
 *   并为这两个通道配置相同的目标Topic来将这一对输入输出指向同一个实际的Topic。
 *   对于上面的错误程序，只需要做如下两处改动：
 *
 * </pre>
 */
public interface ConsumerSink {

    String INPUT = "example-topic-input";

    /**
     * kafkatopic
     *
     * @return object
     */
    @Input(ConsumerSink.INPUT)
    SubscribableChannel input();
}
