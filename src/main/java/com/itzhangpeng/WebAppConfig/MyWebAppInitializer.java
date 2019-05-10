package com.itzhangpeng.WebAppConfig;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:webapp初始化配置类
 * @Version:1.0
 */

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //根容器配置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootContainerConfig.class};
    }

    //webmvc容器配置
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    //前端控制器配置,拦截所有资源，jsp除外
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

   /* @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //向servlet中注册multipartconfig
        MultipartConfigElement element=new MultipartConfigElement("/tmp",5242880l,25623123l,100000);
        registration.setMultipartConfig(element);
    }*/
}
