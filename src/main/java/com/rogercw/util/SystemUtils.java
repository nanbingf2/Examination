package com.rogercw.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by 1 on 2018/6/4.
 */
public class SystemUtils {

    /**
     * 获得当前登录用户的用户名
     * @return
     */
    public static String getCurrentUserName(){
        Subject subject= SecurityUtils.getSubject();
        String username= (String) subject.getPrincipal();
        System.out.println(username);
        return username;
    }

    public static Page setPageNum(Integer pageNo){
        Page p=new Page();
        if(pageNo==null||pageNo==0){
            //查询第一页
            p.setToPageNo(1);
        }else{
            //查询指定页
            p.setToPageNo(pageNo);
        }
        return p;
    }
}
