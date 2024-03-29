package space.mamba.secondskill.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.lakers.util.JacksonUtil;
import space.mamba.secondskill.Kafka.StockProducer;
import space.mamba.secondskill.common.StockWithRedis.RedisKeysConstant;
import space.mamba.secondskill.dao.StockOrderMapper;
import space.mamba.secondskill.pojo.Stock;
import space.mamba.secondskill.pojo.StockOrder;
import space.mamba.secondskill.service.api.OrderService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @auther G.Fukang
 * @date 6/7 12:44
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service(value = "OrderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private StockServiceImpl stockService;

    @Resource
    private StockOrderMapper orderMapper;

    @Resource
    private StockProducer stockProducer;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public int delOrderDBBefore() {
        return orderMapper.delOrderDBBefore();
    }

    @Override
    public int createWrongOrder(int sid) throws Exception {
        Stock stock = checkStock(sid);
        saleStock(stock);
        int res = createOrder(stock);

        return res;
    }

    @Override
    public int createOptimisticOrder(int sid) throws Exception {
        // 校验库存
        Stock stock = checkStock(sid);
        // 乐观锁更新
        saleStockOptimstic(stock);
        // 创建订单
        int id = createOrder(stock);

        return id;
    }

    @Override
    public int createOrderWithLimitAndRedis(int sid) throws Exception {
        // 校验库存，从 Redis 中获取
        Stock stock = checkStockWithRedis(sid);
        // 乐观锁更新库存和Redis
        saleStockOptimsticWithRedis(stock);
        // 创建订单
        int res = createOrder(stock);

        return res;
    }

    @Override
    public void createOrderWithLimitAndRedisAndKafka(int sid) throws Exception {
        // 校验库存
        Stock stock = checkStockWithRedis(sid);
        // 下单请求发送至 kafka，需要序列化 stock
        stockProducer.producer(stock);
        log.info("消息发送至 Kafka 成功");
    }

    @Override
    public int consumerTopicToCreateOrderWithKafka(Stock stock) throws Exception {
        // 乐观锁更新库存和 Redis
        saleStockOptimsticWithRedis(stock);
        int res = createOrder(stock);
        if (res == 1) {
            log.info("Kafka 消费 Topic 创建订单成功");
        } else {
            log.info("Kafka 消费 Topic 创建订单失败");
        }

        return res;
    }

    /**
     * Redis 校验库存
     *
     * @param sid
     */
    private Stock checkStockWithRedis(int sid) throws Exception {
        //TODO
        Integer count = Integer.parseInt(stringRedisTemplate.opsForValue().get(RedisKeysConstant.STOCK_COUNT + sid));
        Integer sale = Integer.parseInt(stringRedisTemplate.opsForValue().get(RedisKeysConstant.STOCK_SALE + sid));
        Integer version = Integer.parseInt(stringRedisTemplate.opsForValue().get(RedisKeysConstant.STOCK_VERSION + sid));
        if (count < 1) {
            log.info("库存不足");
            throw new RuntimeException("库存不足 Redis currentCount: " + sale);
        }
        Stock stock = new Stock();
        stock.setId(sid);
        stock.setCount(count);
        stock.setSale(sale);
        stock.setVersion(version);
        // 此处应该是热更新，但是在数据库中只有一个商品，所以直接赋值
        stock.setName("手机");

        return stock;
    }

    /**
     * 更新数据库和 DB
     */
    private void saleStockOptimsticWithRedis(Stock stock) throws Exception {
        int res = stockService.updateStockByOptimistic(stock);
        if (res == 0) {
            throw new RuntimeException("并发更新库存失败");
        }
        // 更新 Redis
        /**
         * Redis 事务保证库存更新
         * 捕获异常后应该删除缓存
         */

        // Redis 通过multi, exec, discard 操作提供事务支持. RedisTemplate 也同样支持这些操作,
        // 然而 RedisTemplate 不保证在同一个连接中执行事务中的所有操作.
        // 当使用 redis 的事务的时候,  Spring Data Redis 提供 SessionCallback 的接口支持多个操作的执行都在同一个连接中

        // 开始事务
            stringRedisTemplate.executePipelined((RedisCallback) redisConnection -> {
                stringRedisTemplate.opsForValue().decrement(RedisKeysConstant.STOCK_COUNT + stock.getId());
                stringRedisTemplate.opsForValue().increment(RedisKeysConstant.STOCK_SALE + stock.getId());
                stringRedisTemplate.opsForValue().increment(RedisKeysConstant.STOCK_VERSION + stock.getId());
                return null;
            });
    }

    /**
     * 校验库存
     */
    private Stock checkStock(int sid) throws Exception {
        Stock stock = stockService.getStockById(sid);
        if (stock.getCount() < 1) {
            throw new RuntimeException("库存不足");
        }
        return stock;
    }

    /**
     * 扣库存
     */
    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale() + 1);
        stock.setCount(stock.getCount() - 1);
        return stockService.updateStockById(stock);
    }

    /**
     * 乐观锁扣库存
     */
    private void saleStockOptimstic(Stock stock) throws Exception {
        int count = stockService.updateStockByOptimistic(stock);
        if (count == 0) {
            throw new RuntimeException("并发更新库存失败");
        }
    }

    /**
     * 创建订单
     */
    private int createOrder(Stock stock) throws Exception {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        order.setCreateTime(new Date());
        int res = orderMapper.insertSelective(order);
        if (res == 0) {
            throw new RuntimeException("创建订单失败");
        }
        return res;
    }
}
