package space.mamba.common.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pankui
 * @date 2021/7/7
 * <pre>
 *     https://segmentfault.com/a/1190000039974533
 *
 *    如支付的通知应答 不需要统一返回体
 *
 *    如果某个Controller下所有的方法都绕过，就把这个注解标记在控制器类上；
 *    如果只想忽略某个方法上就把它标记在该方法上即可。
 *
 *
 *    异常导致这个注解丢失
 * </pre>
 */
@Documented
@Inherited
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseData {
}
