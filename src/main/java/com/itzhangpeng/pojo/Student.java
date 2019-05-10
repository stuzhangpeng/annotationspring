package com.itzhangpeng.pojo;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/3
 * @Description:com.itcast.zhangpeng
 * @Version:1.0
 */

public class Student {
    private String name;
    private  Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}
