package com.itzhangpeng.exceptionhanler;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:实现接口方式的全局异常处理类
 * @Version:1.0
 */
public class GlobalExceptionHanler implements HandlerExceptionResolver {
    private String message;
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        message="亲，你走丢了";
        if(ex instanceof CustomizerException){
            CustomizerException exception= (CustomizerException) ex;
             message = exception.getMessage();
        }
        ex.printStackTrace();
        ModelAndView mv= new ModelAndView();
        mv.addObject("error", message);
        mv.setViewName("error");
       return mv;
    }
}
