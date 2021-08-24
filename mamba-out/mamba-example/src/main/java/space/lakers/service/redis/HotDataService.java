package space.lakers.service.redis;

import org.springframework.stereotype.Service;

/**
 * @author pankui
 * @date 2019-07-28
 * <pre>
 *      热点数据
 *
 *      热点key
 *
 *      1、mySQL里有2000w数据，redis中只存20w的数据，如何保证redis中的数据都是热点数据?
 *      解决办法一：    提供一种简单实现缓存失效的思路: LRU(最近少用的淘汰)
 *      即redis的缓存每命中一次,就给命中的缓存增加一定ttl(过期时间)(根据具体情况来设定, 比如10分钟).
 *      一段时间后, 热数据的ttl都会较大, 不会自动失效, 而冷数据基本上过了设定的ttl就马上失效了.
 *
 *      解决办法二：
 *          活跃用户：
 *          redis sortSet里 放两天内(为方便取一天内活跃用户)登录过的用户，登录一次ZADD一次，如set已存在则覆盖其分数（登录时间）。
 *          键：login:users，值：分数 时间戳、value userid。设置一个周期任务，
 *          比如每天03:00:00点删除sort set中前一天3点前的数据（保证set不无序增长、留近一天内活跃用户）。
 *          取时，拿到当前时间戳（int 10位），再减1天就可按分数范围取过去24h活跃用户。
 *
 * </pre>
 */
@Service
public class HotDataService {
}
