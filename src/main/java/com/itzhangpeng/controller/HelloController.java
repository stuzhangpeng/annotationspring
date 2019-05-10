package com.itzhangpeng.controller;
import com.itzhangpeng.exceptionhanler.CustomizerException;
import com.itzhangpeng.pojo.User;
import com.itzhangpeng.service.HelloService;
import com.itzhangpeng.service.UploadFileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.UUID;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/5
 * @Description:com.itzhangpeng.controller
 * @Version:1.0
 */
@Controller
public class HelloController {
    @Autowired
    UploadFileService uploadFile;
    @Autowired
    HelloService helloService;
    @RequestMapping("/helloController")
    @ResponseBody
    public  String helloController() throws CustomizerException {
        String s = helloService.helloService();
        //抛异常，全局异常处理器处理
        //int i=1/0;
        //抛自定义异常
        if(0==0)
        throw new CustomizerException("自定义异常");
        return s;
    }
    //测试json乱码
    @RequestMapping("/testEncoding")
    @ResponseBody
    public User testEncoding(HttpServletResponse response) throws IOException {
       User user =new User();
       user.setName("张三");
       user.setAge(652);
       return  user;
    }
    //测试string乱码
    @RequestMapping("/testStringEncoding")
    @ResponseBody
    public String testStringEncoding(HttpServletResponse response) throws IOException {
       return  "字符串乱码";
    }
    //测试string转日期
    @RequestMapping("/testStringToDate")
    @ResponseBody
    public String testStringToDate(Date date){
        System.out.println(date);
        return "转换成功";
    }
    //图片上传
    @RequestMapping("/testUploadFile")
    @ResponseBody
    public String testUploadFile(User user,MultipartFile file)  {
        String  result = uploadFile.uploadFile(file);
        return  result;
    }
    //图片下载
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String file ="C:\\Users\\Administrator\\Desktop\\图片\\美女.jpg";
        InputStream inputStream =new FileInputStream(file);
        String filename = FilenameUtils.getName(file);
        //字节流读取文件
        BufferedInputStream bufferedInputStream =new BufferedInputStream(inputStream);
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedOutputStream bufferedOutputStreamo =new BufferedOutputStream(outputStream);
        //根据浏览器的类型解决中文乱码
        final String userAgent = request.getHeader("USER-AGENT");
        if(userAgent.contains("Firefox")){
            //是火狐浏览器，使用BASE64编码
            filename  = "=?utf-8?b?"+new BASE64Encoder().encode(  filename .getBytes("utf-8"))+"?=";
        }else{
            //其他浏览器，给文件名进行URL编码
            //URLEncoder.encode()需要两个参数
            filename  = URLEncoder.encode(  filename , "utf-8");
        }
        response.setContentType(request.getServletContext().getMimeType(filename));
        //告知客户端义附件形式下载
        response.setHeader("content-disposition","attachment;filename="+filename);
        byte[] buf =new byte[1024];
        int len=0;
        while ((len =bufferedInputStream.read(buf))!=-1){
            //字节流写出到客户端
            bufferedOutputStreamo.write(buf, 0, len);
        }
        //关闭流
        bufferedInputStream.close();
        bufferedOutputStreamo.close();
    }
}
