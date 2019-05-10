package com.itzhangpeng.Converters;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther:zhangpeng
 * @Date:2019/5/7
 * @Description:字符串转日期converter
 * @Version:1.0
 */

public class StringToDateConverter  implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        if (s==null){
            return  null;
        }
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = dateFormat.parse(s);
            return  parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //抛异常返回null
        return null;
    }

}
