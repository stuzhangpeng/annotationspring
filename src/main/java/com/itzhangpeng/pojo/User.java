package com.itzhangpeng.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */

public class User  {
    private String password;
    private String name;
    private Integer age;
    private Integer id;
    private String gender;
    private Date date;
    /**
    *功能描述：User的初始化方法
    *@param:
    *@return:
    *@author:
    */
    @PostConstruct
    public  void  init(){
        System.out.println("init 调用了");

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", gender='" + gender + '\'' +
                ", date=" + date +
                '}';
    }
}
