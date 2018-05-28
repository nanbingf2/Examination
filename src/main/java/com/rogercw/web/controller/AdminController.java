package com.rogercw.web.controller;

import com.rogercw.po.College;
import com.rogercw.po.Student;
import com.rogercw.po.User;
import com.rogercw.po.custom.StudentCustom;
import com.rogercw.service.CollegeService;
import com.rogercw.service.StudentService;
import com.rogercw.service.UserService;
import com.rogercw.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private StudentService studentService;
    @Resource
    private CollegeService collegeService;
    @Resource
    private UserService userService;

    @RequestMapping("/showStudent")
    public String showStudent(Integer page,Model model){
        Page p=new Page();
        if(page==null||page==0){
            //查询第一页
            p.setToPageNo(1);
        }else{
            //查询指定页
            p.setToPageNo(page);
        }
        List<StudentCustom> studentList=studentService.findByPage(p);
        int count=studentService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("studentList",studentList);
        return "admin/showStudent";
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.GET)
    public String addStudentUI(Model model){
        //查询所有院系,以便用户选择
        List<College> collegeList=collegeService.findAllCollege();
        model.addAttribute("collegeList",collegeList);
        return "admin/addStudent";
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public String addStudent(StudentCustom studentCustom,Model model){
        boolean flag=studentService.save(studentCustom);
        if(!flag){
            //数据库中已经存在该学号
            model.addAttribute("message","学号已经存在,请重新输入");
            return "error";
        }
        //在学生表中保存成功
        //同时将该学生注册到用户表中,
        User user=new User();
        user.setUsername(studentCustom.getStudentid().toString());
        user.setPassword("123");
        user.setRole(1);
        userService.save(user);
        return "redirect:/admin/showStudent";
    }


}
