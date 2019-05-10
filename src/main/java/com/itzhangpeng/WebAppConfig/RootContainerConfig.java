package com.itzhangpeng.WebAppConfig;

import com.itzhangpeng.pojo.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:springConfig
 * @Version:1.0
 */
//排除controller和RestController注解
@ComponentScan(value = {"com.itzhangpeng"},excludeFilters = {
      @ComponentScan.Filter(type = FilterType.ANNOTATION,classes ={Controller.class, RestController.class} )
})
//开启注解事务
@EnableTransactionManagement
//开启注解aop
//暴露代理对象，并设置代理方式为cglib代理
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
@Configuration
public class RootContainerConfig {
    @Bean
    public DataSource devdataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置数据源
        dataSource.setUser("root");
        dataSource.setPassword("123");
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql:///mybaits?characterEncoding=utf8&serverTimezone=UTC");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) throws PropertyVetoException {
        JdbcTemplate jdbcTemplate=new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    //注册事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager() throws PropertyVetoException {
        DataSourceTransactionManager transactionManager =new DataSourceTransactionManager();
        //添加数据源
        transactionManager.setDataSource( devdataSource());
        return transactionManager;
    }
    //注册事务管理器
    @Bean
    public User user() {
        User u =new User();
        u.setAge(20);
        u.setName("杨过");
        return u;

    }
}
