package com.zhuzichu.blog.core.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *md5工具类
 */
@Component
public class ProjectMD5Utils {
    private static final char[] hexDigits = {'0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    public final static String getMD5String(String s) {

        byte[] btInput = s.getBytes();
        //获得MD5摘要算法的 MessageDigest 对象
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            //unreachable
        }
        //使用指定的字节更新摘要
        mdInst.update(btInput);
        //获得密文
        byte[] md = mdInst.digest();
        //把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);

    }

}
