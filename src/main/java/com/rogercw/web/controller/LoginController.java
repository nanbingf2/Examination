package com.rogercw.web.controller;

import com.rogercw.po.User;
import com.rogercw.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        User user=userService.findUserByUserNameAndPassword(username,password);
        if(user!=null){
            //将用户保存到session中
            session.setAttribute("user",user);
            //return "redirect:/admin/showStudent";
            return "redirect:/student/showCourse";
        }else{
            return "loginUI";
        }
    }
}
