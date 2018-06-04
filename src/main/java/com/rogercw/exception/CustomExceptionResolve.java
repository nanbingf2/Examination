package com.rogercw.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 2018/6/4.
 * 全局异常处理器：全局异常处理器只有一个
 */
public class CustomExceptionResolve implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv=new ModelAndView();
        CustomException exception;
        String message;
        if (e instanceof CustomException){
            exception=(CustomException) e;
        }else if(e instanceof UnknownAccountException) {
            message=e.getMessage();
            mv.addObject("message",message);
            mv.setViewName("error");
            return mv;
        }else if(e instanceof IncorrectCredentialsException){
            message=e.getMessage();
            mv.addObject("message",message);
            mv.setViewName("error");
            return mv;
        }else{
            exception=new CustomException("未知错误");
        }

        message=exception.getMessage();
        mv.addObject("message",message);
        mv.setViewName("error");
        return mv;
    }
}
