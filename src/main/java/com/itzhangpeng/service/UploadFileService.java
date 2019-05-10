package com.itzhangpeng.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/8
 * @Description:处理文件上传service
 * @Version:1.0
 */
@Service
public class UploadFileService {
    //处理上传文件
    public String uploadFile(MultipartFile file){
        //获得文件扩展名
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String uuid= UUID.randomUUID().toString();
        String radomString = uuid.replaceAll("-", "");
        //生成文件名字
        String fileName=radomString+"."+extension;
        try {
            //把文件保存到固定文件夹
            file.transferTo(new File("F:\\upload\\"+fileName)) ;
            //返回文件名
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //上传失败返回值
        return "上传失败";
    }
}
