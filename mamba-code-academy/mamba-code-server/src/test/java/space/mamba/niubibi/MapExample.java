package space.mamba.niubibi;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pankui
 * @date 2019-08-21
 * <pre>
 *      map
 * </pre>
 */
public class MapExample {

    public static void main(String[] args) {
        Map<Long,Object> map = new HashMap<>(3);
        map.put(1L,1);
        map.put(2L,1);
        map.put(3L,1);

        // HahMap 的 get 方法判断 key.equals(k) 是否相等，而equals 则是根据 instance 来判断  if (obj instanceof Long) {}
        System.out.println("结果1:"+map.get(1));

        System.out.println("结果2:"+map.get(1L));

    }
}
