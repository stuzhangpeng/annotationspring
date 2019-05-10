package com.itzhangpeng.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**aop 目标类
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */
@Service
public class MathCaculater {
    public String testAop(){
        //内部调用方法，通过this调用，此处this为目标对象，将导致被调用方法无法aop
        //此处通过AopContext.currentProxy()获取代理对象调用进行aop，需要设置exposeproxy=true
        //暴露代理对象才能获得
        String s = ((MathCaculater)AopContext.currentProxy()).sayHello();
        //String s = sayHello();
        return s;
    }
    //目标方法
    public String sayHello(){
        System.out.println("sayhello");
        int i=1/0;
        return "caculate";
    }
}
