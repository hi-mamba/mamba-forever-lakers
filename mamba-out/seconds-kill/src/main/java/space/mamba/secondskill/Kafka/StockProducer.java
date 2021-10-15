package space.mamba.secondskill.Kafka;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import space.mamba.secondskill.Kafka.channel.StockChannel;
import space.mamba.secondskill.pojo.Stock;

import javax.annotation.Resource;

/**
 * @author mini mamba
 * @date 2021/7/12
 * <pre>
 *
 * </pre>
 */
@Component
public class StockProducer {

    @Resource
    private StockChannel commonChannel;

    public void producer(Stock stock) {
        MessageChannel messageChannel = commonChannel.publisher();
        messageChannel.send(MessageBuilder.withPayload(stock).build());
    }
}
