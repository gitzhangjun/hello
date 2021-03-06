package com.shop.course.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result<?> defaultExceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof BusinessException) {
            log.error("业务异常：" + e.getMessage(), this.getClass());
            BusinessException businessException = (BusinessException)e;
            return ResultUtil.error(businessException.getCode(), businessException.getMessage());
        }
        //未知错误
        return ResultUtil.error(-1, "系统异常：\\n"+e);
    }
}
