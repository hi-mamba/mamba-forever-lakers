package space.mamba.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import space.mamba.common.annotion.IgnoreResponseData;
import space.mamba.common.utils.JacksonUtils;
import space.mamba.vo.ResponseData;

/**
 * @author mamba
 */
@Slf4j
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    private String[] swagger = new String[]{"swagger", "docs"};

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        //这个方法如果返回false就表示不执行统一返回体的封装逻辑
        //如果异常，这里获取不到这个注解
        return !returnType.hasMethodAnnotation(IgnoreResponseData.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<?
            extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse
                                          response) {
        ResponseData<Object> responseData;
        try {
            String requestUrl = request.getURI().toString();
            if (StringUtils.containsAny(requestUrl, swagger)) {
                return body;
            }

            //返回值为空 或者 string 的情况
            // 字符串在不声明Content-Type的情况下优先使用StringHttpMessageConverter ，就导致了转换异常
            // https://segmentfault.com/a/1190000039974533
            if (body == null || body instanceof String) {
                responseData = new ResponseData<>(body);
                //TODO 优化,或者这样判断判断 selectedConverterType.equals(StringHttpMessageConverter.class)
                if (returnType.getMethod().getReturnType().getName().equals(String.class.getName())) {
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    return JacksonUtils.toJSONString(responseData);
                }
                return responseData;
            }
            //防止重复包裹的问题出现
            if (body instanceof ResponseData) {
                return body;
            }

            //处理异常
            if (body instanceof Exception) {
                return new ResponseData<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value() + "", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }

            int status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();

            if (status != HttpStatus.OK.value()) {
                responseData = new ResponseData<>(false, String.valueOf(status), null, body);
                return responseData;
            }
            //正常返回
            return new ResponseData<>(body);
        } catch (Exception e) {
            log.error("beforeBodyWrite error", e);
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseData<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value() + "", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }
}
