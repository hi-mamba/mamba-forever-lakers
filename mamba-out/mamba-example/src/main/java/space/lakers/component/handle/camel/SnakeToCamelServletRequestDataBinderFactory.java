package space.lakers.component.handle.camel;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ServletRequestDataBinderFactory;

import java.util.List;

/**
 * @author mini kobe
 * @date 2019/8/24
 * <pre>
 *  下划线风格命名转换为驼峰
 * </pre>
 */
public class SnakeToCamelServletRequestDataBinderFactory extends ServletRequestDataBinderFactory {

    /**
     * Create a new instance.
     *
     * @param binderMethods one or more {@code @InitBinder} methods
     * @param initializer   provides global data binder initialization
     */
    public SnakeToCamelServletRequestDataBinderFactory(
            List<InvocableHandlerMethod> binderMethods,
            WebBindingInitializer initializer) {
        super(binderMethods, initializer);
    }

    /**
     * Extension point to create the WebDataBinder instance. By default this is {@code
     * WebRequestDataBinder}.
     *
     * @param target     the binding target or {@code null} for type conversion only
     * @param objectName the binding target object name
     * @param webRequest the current request
     * @throws Exception in case of invalid state or arguments
     */
    @Override
    protected ServletRequestDataBinder createBinderInstance(Object target, String objectName,
                                                            NativeWebRequest webRequest) {
        return new SnakeToCamelRequestDataBinder(target, objectName);
    }
}
