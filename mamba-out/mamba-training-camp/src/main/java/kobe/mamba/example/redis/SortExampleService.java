package kobe.mamba.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author mamba
 * @date 2021/4/24
 *
 * <pre>
 *  ## 题目：考试根据分数 和 交卷时间排名，分数一样，交卷早的排面在前
 *
 * 考试时间：9：00 - 11：00
 *
 *     分数, 交卷时间, 排名
 *     90    11:00   3
 *     90    11:00   3
 *     90    10:30   2
 *     90    10:20   1
 *     80    10:20   5
 *     80    10:10   4
 *
 * </pre>
 *
 *  解决方案
 */
@Slf4j
@Service
public class SortExampleService {

    @Resource
    private RedisTemplate redisTemplate;

    public void sortClass() {
        //24:00 转换成整数,交卷时间，迟到交卷不考虑
        //将学生数据按照分数高低进行排序，value -- 姓名， score -- 分数
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("student", "张三", Integer.parseInt(90 + "" + getSubmitClass("11:00")));
        zSetOperations.add("student", "李四", Integer.parseInt(90 + "" + getSubmitClass("11:00")));
        zSetOperations.add("student", "王五", Integer.parseInt(90 + "" + getSubmitClass("10:30")));
        zSetOperations.add("student", "钱六", Integer.parseInt(90 + "" + getSubmitClass("10:20")));
        zSetOperations.add("student", "赵赵", Integer.parseInt(80 + "" + getSubmitClass("11:00")));
        zSetOperations.add("student", "潘潘", Integer.parseInt(80 + "" + getSubmitClass("10:30")));
        zSetOperations.add("student", "毛毛", Integer.parseInt(80 + "" + getSubmitClass("10:10")));
        zSetOperations.add("student", "紫霞", Integer.parseInt(50 + "" + getSubmitClass("11:00")));
        zSetOperations.add("student", "自尊宝", Integer.parseInt(30 + "" + getSubmitClass("10:40")));
        zSetOperations.add("student", "青霞", Integer.parseInt(20 + "" + getSubmitClass("10:10")));

        //取 0 分到 最大值
        Set<String> sets = zSetOperations.rangeByScore("student", 0, Integer.MAX_VALUE);

        System.out.println(sets);
    }

    private int getSubmitClass(String time) {
        int result = 2400 - Integer.parseInt(time.replace(":", ""));
        System.out.println(time + "getSubmitClass=" + result);
        return result;
    }
}
