package com.rogercw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1 on 2018/5/27.
 */
@Controller
public class LoginController {

    @RequestMapping("/loginUI")
    public String loginUI(){
        return "../../loginUI";
    }
}
