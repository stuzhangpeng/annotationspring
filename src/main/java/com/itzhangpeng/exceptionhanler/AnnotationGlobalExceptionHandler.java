package com.itzhangpeng.exceptionhanler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/6
 * @Description:注解式全局异常处理器
 * @Version:1.0
 */
@RestControllerAdvice
public class AnnotationGlobalExceptionHandler {
    @ExceptionHandler
   public String handlerException(Exception ex){
       ex.printStackTrace();
       return  "exception handing";
   }

}
