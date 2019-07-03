package com.itzhangpeng.service;

import com.itzhangpeng.dao.UserDao;
import com.itzhangpeng.exceptionhanler.CustomizerException;
import com.itzhangpeng.pojo.User;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */
@Service
public class UserService {
    @Autowired
    private User user;
    @Autowired
    private UserDao userDao;
    //配置事务
    /*
     *    事务的传播行为
     *     1、PROPAGATION_REQUIRED：默认事务类型，如果没有，就新建一个事务；如果有，就加入当前事务。适合绝大多数情况。
     *
     *     2、PROPAGATION_REQUIRES_NEW：如果没有，就新建一个事务；如果有，就将当前事务挂起。
     *
     *     3、PROPAGATION_NESTED：如果没有，就新建一个事务；如果有，就在当前事务中嵌套其他事务。
     *
     *     4、PROPAGATION_SUPPORTS：如果没有，就以非事务方式执行；如果有，就使用当前事务。
     *
     *     5、PROPAGATION_NOT_SUPPORTED：如果没有，就以非事务方式执行；如果有，就将当前事务挂起。即无论如何不支持事务。
     *
     *     6、PROPAGATION_NEVER：如果没有，就以非事务方式执行；如果有，就抛出异常。
     *
     *     7、PROPAGATION_MANDATORY：如果没有，就抛出异常；如果有，就使用当前事务。
     */
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.REPEATABLE_READ  )
    public void saveUser()  {
        userDao.saveUser(user);
        throw new RuntimeException("jjjj");
        //事务默认抛出运行时异常和unchecked异常，事务才会回滚.抛出检查异常不会回滚
        //rollbackFor修改回滚的异常，抛出该异常会回滚
        //内部非事务方法调用内部事务方法。事务将失效，可以通过代理对象调用内部方法执行事务
        //expose-Proxy=true暴露代理对象，AopContext.currentProxy()来获取代理对象
       // throw new CustomizerException("自定义异常");
       /* try {
            //捕获异常
            throw new CustomizerException("自定义异常");
        } catch (CustomizerException e) {
            e.printStackTrace();
            //抛出运行时异常
            throw new  RuntimeException("paochuyunxingshiyichang");
        }*/

        //
    }
    public void deleteUserById(Integer id){

    }
  @Transactional(propagation = Propagation.REQUIRED,
         isolation = Isolation.REPEATABLE_READ  )
    public void testTransction() {
        try {
            ((UserService)AopContext.currentProxy()). saveUser();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public void findUserById(Integer id){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
