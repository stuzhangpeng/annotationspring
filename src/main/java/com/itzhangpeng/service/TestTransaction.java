package com.itzhangpeng.service;
import com.itzhangpeng.exceptionhanler.CustomizerException;
import com.itzhangpeng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**测试注解式声明事务
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */
public class TestTransaction {
    @Autowired
    private  User user;
    @Autowired
    private AnnotationConfigApplicationContext context;
    public void test() throws CustomizerException {
        UserService bean = context.getBean(UserService.class);
        bean.setUser(user);
        int i =1/0;
       bean.saveUser();
    }

}
