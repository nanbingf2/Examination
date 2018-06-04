package com.rogercw.web.controller;

import com.rogercw.exception.CustomException;
import com.rogercw.po.User;
import com.rogercw.service.UserService;
import com.rogercw.util.SystemUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by 1 on 2018/6/4.
 */
@Controller
public class PasswordController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/editPassword",method = RequestMethod.POST)
    public String editPassword(String password,String password1) throws CustomException {
        //获取当前登录用户的密码
        //User user= (User) session.getAttribute("user");
        String username= SystemUtils.getCurrentUserName();
        User user = userService.findByUserName(username);
        String pwd=user.getPassword();
        if(!pwd.equals(password)){
            //输入的旧密码与当前用户的密码错误
            //String message="输入的密码错误";
            //model.addAttribute("message",message);
            //return "admin/editPassword";
            throw new CustomException("输入的旧密码错误");
        }
        //重新设置密码并更行当前用户
        user.setPassword(password1);
        userService.updateUser(user);
        //session.setAttribute("user",user);
        return "success";
    }
}
