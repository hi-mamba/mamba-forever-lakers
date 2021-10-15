package space.mamba.secondskill.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import space.mamba.secondskill.common.StockWithRedis.RedisKeysConstant;
import space.mamba.secondskill.pojo.Stock;
import space.mamba.secondskill.service.api.StockService;

import javax.annotation.Resource;

/**
 * @auther G.Fukang
 * @date 6/8 13:41
 * 缓存预热，在 Spring Boot 启动后立即执行此方法
 */
@Slf4j
@Component
public class RedisPreheatRunner implements ApplicationRunner {

    @Resource
    private StockService stockService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 从数据库中查询热卖商品
        Stock stock = stockService.getStockById(1);
        if (stock == null) {
            log.info("## 记录不存在");
            return;
        }
        log.info("## 缓存预热开始");
        // 删除旧缓存
        stringRedisTemplate.delete(RedisKeysConstant.STOCK_COUNT + stock.getCount());
        stringRedisTemplate.delete(RedisKeysConstant.STOCK_SALE + stock.getSale());
        stringRedisTemplate.delete(RedisKeysConstant.STOCK_VERSION + stock.getVersion());
        //缓存预热
        int sid = stock.getId();
        stringRedisTemplate.opsForValue().set(RedisKeysConstant.STOCK_COUNT + sid, String.valueOf(stock.getCount()));
        stringRedisTemplate.opsForValue().set(RedisKeysConstant.STOCK_SALE + sid, String.valueOf(stock.getSale()));
        stringRedisTemplate.opsForValue().set(RedisKeysConstant.STOCK_VERSION + sid, String.valueOf(stock.getVersion()));
    }
}
