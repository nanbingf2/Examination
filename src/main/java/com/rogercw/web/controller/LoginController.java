package com.rogercw.web.controller;

import com.rogercw.po.User;
import com.rogercw.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 1 on 2018/5/27.
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping("/loginUI")
    public String loginUI(){
        return "loginUI";
    }

    /*@RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user=userService.findUserByUserNameAndPassword(username,password);
        if(user!=null){
            //将用户保存到session中
            session.setAttribute("user",user);
            //return "redirect:/admin/showStudent";
            //return "redirect:/student/showCourse";
            return "redirect:/teacher/showCourse";
        }else{
            return "loginUI";
        }
    }*/


    //登录方法
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username,String password){
        //获得当前对象
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        //调用login方法进行登录
        subject.login(token);
        //角色判断
        if(subject.hasRole("admin")){
            return "redirect:/admin/showStudent";
        }else if(subject.hasRole("student")){
            return "redirect:/student/showCourse";
        }else if(subject.hasRole("teacher")){
            return "redirect:/teacher/showCourse";
        }

        return "loginUI";
    }

}
