package space.mamba.secondskill.Kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import space.mamba.secondskill.Kafka.channel.StockChannel;
import space.mamba.secondskill.pojo.Stock;
import space.mamba.secondskill.service.api.OrderService;

import javax.annotation.Resource;


/**
 * @author G.Fukang
 * @date 6/12 19:17
 */
@Slf4j
@EnableBinding(value = StockChannel.class)
public class StockConsumer {

    @Resource
    private OrderService orderService;

    @StreamListener(StockChannel.INPUT)
    public void handleMessage(@Payload Stock stock) throws Exception {
        log.info("Received stock: {}", stock);
        // 创建订单
        orderService.consumerTopicToCreateOrderWithKafka(stock);
    }
}
