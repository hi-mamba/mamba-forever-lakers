package space.mamba.service.redis;

import org.springframework.stereotype.Service;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *   缓存并发问题
 *
 *   如何解决redis的并发竞争key问题呢？下面给到2个Redis并发竞争的解决方案。
 *
 *   第一种方案：分布式锁+时间戳
 *
 *   第二种方案：利用消息队列
 * </pre>
 */
@Service
public class CacheConcurrentService {
}
