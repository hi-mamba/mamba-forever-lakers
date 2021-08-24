package space.lakers.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * Description
 * <p>
 * 异常拦截器
 *
 * @author mamba
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MissingServletRequestParameterException.class, HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IllegalAccessException handleMissingServletRequestParameterException(HttpServletRequest request,
                                                                                MissingServletRequestParameterException e) {
        String url = String.valueOf(request.getRequestURL());
        log.info("url={}", url, e);
        IllegalAccessException illegalAccessException = new IllegalAccessException();
        return illegalAccessException;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception handleGlobalException(HttpServletRequest request, Exception e) {
        String url = String.valueOf(request.getRequestURL());
        //处理限流Exception
        log.info("url={}", url, e);
        return e;
    }
}