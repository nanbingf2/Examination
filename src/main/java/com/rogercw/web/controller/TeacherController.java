package com.rogercw.web.controller;

import com.rogercw.po.Student;
import com.rogercw.po.User;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.SelectCourseCustom;
import com.rogercw.service.CourseService;
import com.rogercw.service.SelectCourseService;
import com.rogercw.service.StudentService;
import com.rogercw.util.CodingUtil;
import com.rogercw.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2018/5/31.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private CourseService courseService;
    @Resource
    private SelectCourseService selectCourseService;
    @Resource
    private StudentService studentService;

    //显示所有的课程,分页
    @RequestMapping("/showCourse")
    public String showCourse(Integer page, Model model,
                             CourseCustom course, HttpSession session) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //解决get方式乱码问题
        String coursename= CodingUtil.encode(course.getCoursename());
        course.setCoursename(coursename);

        //获取当前教师的编号
        User user= (User) session.getAttribute("user");
        //设置过滤条件(教师编号)
        course.setTeacherid(Integer.parseInt(user.getUsername()));
        List<CourseCustom> courseList=courseService.findByPage(p,course);
        int count=courseService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("courseList",courseList);
        model.addAttribute("coursename",coursename);
        return "teacher/showCourse";
    }


    @RequestMapping("/showGrade")
    public String showGrade(Integer page, Integer courseid, Model model,HttpSession session){
        Page p=new Page();
        this.setPageNo(page,p);
        if(courseid==null){
            return "teacher/showCourse";
        }
        List<SelectCourseCustom> selectedCourseList = selectCourseService.findGradeByCourseId(p, courseid);
        //对成绩进行处理
        for (SelectCourseCustom course : selectedCourseList) {
            if(course.getMark()==null){
                course.setOver(false);
            }else{
                course.setOver(true);
            }
        }

        int count=courseService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("courseid",courseid);
        model.addAttribute("selectedCourseList",selectedCourseList);
        return "teacher/showGrade";
    }


    @RequestMapping(value = "/mark",method = RequestMethod.GET)
    public String markUI(SelectCourseCustom selectCourse,Model model){
        //根据学生id查询学生,以便数据回显
        Student student=studentService.findById(selectCourse.getStudentid());
        selectCourse.setStudentname(student.getStudentname());
        model.addAttribute("selectCourse",selectCourse);
        System.out.println(selectCourse.getStudentname()+selectCourse.getStudentid());
        return "/teacher/mark";
    }

    @RequestMapping(value = "/mark",method = RequestMethod.POST)
    public String mark(SelectCourseCustom selectCourseCustom){
        selectCourseService.updateSelectCourse(selectCourseCustom);
        return "redirect:/teacher/showGrade?courseid="+selectCourseCustom.getCourseid();
    }


    private void setPageNo(Integer pageNo,Page p){
        if(pageNo==null||pageNo==0){
            //查询第一页
            p.setToPageNo(1);
        }else{
            //查询指定页
            p.setToPageNo(pageNo);
        }
    }
}
