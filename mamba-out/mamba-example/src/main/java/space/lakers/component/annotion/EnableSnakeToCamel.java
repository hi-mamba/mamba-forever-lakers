package space.lakers.component.annotion;

import java.lang.annotation.*;

/**
 * @author pankui
 * @date 2019/8/23
 * <pre>
 *      开启 入参 下划线分格命名转换为驼峰
 *
 *      https://github.com/lemonzone2010/springmvc-demo/blob/master/src/main/java/com/example/springmvcdemo/controller/UserOrderController.java
 * </pre>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.PARAMETER})
@Inherited
@Documented
public @interface EnableSnakeToCamel {
}
