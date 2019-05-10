package com.itzhangpeng.dao;
import com.itzhangpeng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(User user) {
        String sql = "insert into user(name,age) values(?,?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge());
    }

    public void deleteUserById(Integer id) {

    }

    public void findUserById(Integer id) {

    }
}
