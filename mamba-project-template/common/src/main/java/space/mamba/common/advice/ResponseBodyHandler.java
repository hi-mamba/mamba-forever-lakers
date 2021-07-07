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
        return true;
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

            if (body instanceof ResponseData) {
                return body;
            }

            //处理异常
            if (body instanceof Exception) {
                return new ResponseData<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value() + "", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }

            int status = ((ServletServerHttpResponse) response).getServletResponse().getStatus();
            //标准http请求异常
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
