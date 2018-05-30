package com.rogercw.web.controller;

import com.rogercw.exception.CustomException;
import com.rogercw.po.SelectCourse;
import com.rogercw.po.User;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.service.CourseService;
import com.rogercw.service.SelectCourseService;
import com.rogercw.util.CodingUtil;
import com.rogercw.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by 1 on 2018/5/30.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private CourseService courseService;
    @Resource
    private SelectCourseService selectCourseService;

    //显示所有的课程,分页
    @RequestMapping("/showCourse")
    public String showCourse(Integer page, Model model, CourseCustom course) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //解决get方式乱码问题
        String coursename= CodingUtil.encode(course.getCoursename());
        course.setCoursename(coursename);
        List<CourseCustom> courseList=courseService.findByPage(p,course);
        int count=courseService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("coursename",coursename);
        model.addAttribute("courseList",courseList);
        return "student/showCourse";
    }

    //执行选课操作
    @RequestMapping("/stuSelectedCourse")
    public String stuSelectedCourse(Integer id, HttpSession session) throws Exception {
        //获取当前登录学生的studentname(学生的编号)
        User user= (User) session.getAttribute("user");
        //根据学生编号获取该学生已选的课程
        List<SelectCourse> selectCourseList=selectCourseService.findSelectCourse(user.getUserid());
        for(SelectCourse selectCourse : selectCourseList){
            if(id==selectCourse.getCourseid()){
                //当前课程已经被选
                throw new CustomException("当前课程已经选了,请重新选择其他课程");
            }
        }
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setCourseid(id);
        selectCourse.setStudentid(user.getUserid());
        selectCourseService.save(selectCourse);
        return "redirect:/student/selectedCourse";
    }


    @RequestMapping("/selectedCourse")
    public String selectedCourse(){
        return "student/selectedCourse";
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
