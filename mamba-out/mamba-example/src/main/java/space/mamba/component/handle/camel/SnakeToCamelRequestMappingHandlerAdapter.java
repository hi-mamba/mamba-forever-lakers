package space.mamba.component.handle.camel;

import org.springframework.web.method.annotation.InitBinderDataBinderFactory;
import org.springframework.web.method.support.InvocableHandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * @author pankui
 * @date 2019/8/24
 * <pre>
 *      下划线风格命名转换为驼峰
 * </pre>
 */
public class SnakeToCamelRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

    @Override
    protected InitBinderDataBinderFactory createDataBinderFactory(
            List<InvocableHandlerMethod> binderMethods)
            throws Exception {
        return new SnakeToCamelServletRequestDataBinderFactory(binderMethods,
                getWebBindingInitializer());
    }
}
