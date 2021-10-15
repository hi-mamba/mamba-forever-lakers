package space.lakers.mq.kafka.produce.topic;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author mini kobe
 * @date 2019-08-19
 * <pre>
 *      output通道
 *
 *      @EnableBinding注解接收一个或者多个接口类型的参数（在这个例子里面，参数是单个Sink接口）。
 *      接口参数声明了输入和/或输出通道。Spring Cloud Stream提供了Source、Sink和Process接口。
 *      你也可以定义你自己的接口。
 *
 *
 *      http://blog.didispace.com/spring-cloud-starter-finchley-7-1/
 *
 *      由于
 *
 * </pre>
 */
public interface ProducerSource {

    /**
     * 为了一个项目能自产自销，自己生产TOPIC ，自己消费
     * TOPIC 的名字写在配置文件,当然了，如果以后有多个TOPIC 那么这样写是需要优化的
     */
    String OUTPUT = "example-topic-output";

    /**
     * kafkatopic
     *
     * @return object
     * @Output注解标识了一个输出通道，应用程序通过它发布消息, 如果没有定义名字，默认会使用被注解的方法名
     */
    @Output(ProducerSource.OUTPUT)
    MessageChannel topicOutput();

}
