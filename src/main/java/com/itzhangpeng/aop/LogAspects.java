package com.itzhangpeng.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:aop通知
 * @Version:1.0
 */
@Component
@Aspect
public class LogAspects {

    @Pointcut("execution(* com.itzhangpeng.service.MathCaculater.sayHello(..))")
    public void pointcut(){

    }

    @AfterReturning("pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @Before("pointcut()")
    public void start(){
        System.out.println("before");
    }
    @After("pointcut()")
    public void after(){
        System.out.println("after");

    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(){
        System.out.println("AfterThrowing");

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around前");

        Object proceed = pjp.proceed();
        System.out.println("around后");
        return proceed;

    }
}
