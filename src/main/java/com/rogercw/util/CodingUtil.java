package com.rogercw.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by 1 on 2018/5/29.
 */
public class CodingUtil {
    public static String getEncoding(String str) {
        String encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
                String s = encode;
                return s; // 是的话，返回“GB2312“，以下代码同理
            }
        } catch (Exception exception) {
        }
        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "no charSet matched"; // 如果都不是，说明输入的内容不属于常见的编码格式。
    }


    public static String encode(String str) throws UnsupportedEncodingException {
        if (str != null) {
            String encoding= getEncoding(str);
            if (encoding=="ISO-8859-1"){
                str=new String(str.getBytes(encoding),"utf-8");
            }
            return str;
        }
        return null;
    }
}
