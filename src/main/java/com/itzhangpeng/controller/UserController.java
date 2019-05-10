package com.itzhangpeng.controller;

import com.itzhangpeng.exceptionhanler.CustomizerException;
import com.itzhangpeng.service.MathCaculater;
import com.itzhangpeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:com.itzhangpeng.controller
 * @Version:1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MathCaculater caculater;
    @RequestMapping("/saveUser")
    public String saveUser() throws CustomizerException {
        userService.saveUser();
        return "success";
    }
    @RequestMapping("/testAop")
    public String testAop(){
        System.out.println(caculater);
        String s = caculater.testAop();
        return s;
    }
    @RequestMapping("/testTransaction")
    public String testTransaction() throws CustomizerException {
        userService.testTransction();
        return "测试事务";
    }
    @ExceptionHandler
    public  String  hanlerException(Exception ex){
        System.out.println(111);
        System.out.println(ex);
        return "局部异常处理";
    }
}
