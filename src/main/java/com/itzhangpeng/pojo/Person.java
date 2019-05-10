package com.itzhangpeng.pojo;

import org.springframework.stereotype.Component;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */
@Component
public class Person {
    private  User  user1 ;
    private String name;
    private int age;

    public User getUser() {
        return user1;
    }

    public void setUser(User user) {
        this.user1 = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "user=" + user1+
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
