package com.itzhangpeng.WebAppConfig;
import com.itzhangpeng.Converters.StringToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:springmvcConfig，注解定制springmvc配置
 * @Version:1.0
 */
//只扫描controller和restController注解的类
@ComponentScan(value = {"com.itzhangpeng"}, includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, RestController.class})
}, useDefaultFilters = false)
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //配置视图解析器，使用jsp
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/Views/", ".jsp");
    }

    //配置静态资源放行，将静态资源交给Servlet容器的默认servlet处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*//配置异常处理器
        @Override
        public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
            resolvers.add(new GlobalExceptionHanler());
        }*/
    //配置静态资源映射。依然由springmvc来处理该类请求
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    //在默认注册的converters中做定制
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*修改默认的StringHttpMessageConverter的字符集
        *注意!!!!!此处不能直接添加自定义的StringHttpMessageConverter组件否则不起作用。
        * 系统还会使用默认的StringHttpMessageConverter组件
        * 也不能移除该组件再添加自定义的converters组件，因为系统使用的是ioc容器中的对象
        * 移除list中的组件仅仅是移除list中的引用，容器中仍然存在,还是会使用默认的
        * 默认添加的七大converters
        * [org.springframework.http.converter.ByteArrayHttpMessageConverter@69aa51,
          org.springframework.http.converter.StringHttpMessageConverter@1423be9,
          org.springframework.http.converter.ResourceHttpMessageConverter@1e474ea,
          org.springframework.http.converter.ResourceRegionHttpMessageConverter@312e95,
         org.springframework.http.converter.xml.SourceHttpMessageConverter@1c13c60,
         org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter@14ac002,
          org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter@1a3b7e5]
        * 修改默认字符串converter的编码表
        * 方式二直接不注册默认的Converter再添加 使用configMessageConverters方法
        */
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setDefaultCharset(Charset.forName("utf-8"));
            }
        }
        //直接添加StringHttpMessageConverter不起作用 converters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
        //添加jsonconverter用来返回json数据
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        converters.add(jsonConverter);

    }

    //添加formatters和converters,作数据格式转换
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
    }

    //路径映射
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置/url.* 访问路径能否匹配 /url方法,默认为true 匹配
        configurer.setUseSuffixPatternMatch(false);
        //设置/url/ 访问路径能否匹配 /url方法,默认为true 匹配
        configurer.setUseTrailingSlashMatch(false);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    //配置跨越访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //cors跨域设置
        //配置允许跨域的接口/**
        //配置允许跨域的源http://localhost:8081
        //配置是否能携带处理(true).
        //配置跨域请求预检请求的有效时间为3600s
        registry.addMapping("/**").allowCredentials(true).
                allowedMethods("get", "post", "put", "delete").allowedOrigins("http://localhost:8081", "null").maxAge(3600);
    }

    //添加视图处理器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/success").setViewName("success");
    }

    //添加参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

    }

    //添加返回值处理器
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    //配置multiPart解析器
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(5 * 1024 * 1024);
        return commonsMultipartResolver;
    }

}