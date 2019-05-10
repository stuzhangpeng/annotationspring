package com.itzhangpeng.controller;
import com.itzhangpeng.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:com.itzhangpeng.controller
 * @Version:1.0
 */
@Controller
public class ViewController {
    @RequestMapping("/helloViewController")
    public  String helloController(){
        return "hello";
    }
}
