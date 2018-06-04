package com.rogercw.web.controller;

import com.rogercw.exception.CustomException;
import com.rogercw.po.SelectCourse;
import com.rogercw.po.User;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.SelectCourseCustom;
import com.rogercw.service.CourseService;
import com.rogercw.service.SelectCourseService;
import com.rogercw.service.UserService;
import com.rogercw.util.CodingUtil;
import com.rogercw.util.Page;
import com.rogercw.util.SystemUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
 * Created by 1 on 2018/5/30.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private CourseService courseService;
    @Resource
    private SelectCourseService selectCourseService;
    @Resource
    private UserService userService;

    //显示所有的课程,分页
    @RequestMapping("/showCourse")
    public String showCourse(Integer page, Model model,
                             CourseCustom course,HttpSession session) throws UnsupportedEncodingException {

        Page p=SystemUtils.setPageNum(page);
        //解决get方式乱码问题
        String coursename= CodingUtil.encode(course.getCoursename());
        course.setCoursename(coursename);
        List<CourseCustom> courseList=courseService.findByPage(p,course);
        int count=courseService.findAllCount(course);
        p.setTotalPage(count);

        //设置页面要显示的数据
        //1：查询正在学的课程数量
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setMark(0);
        selectCourse.setStudentid(Integer.parseInt(SystemUtils.getCurrentUserName()));
        int selectCourseCount=selectCourseService.selectCount(selectCourse);
        //2:查询已修完的课程数量
        selectCourse.setMark(1);
        int finishedCourseCount=selectCourseService.selectCount(selectCourse);
        model.addAttribute("Page",p);
        session.setAttribute("allCourseCount",count);
        session.setAttribute("selectCourseCount",selectCourseCount);
        session.setAttribute("finishedCourseCount",finishedCourseCount);
        model.addAttribute("coursename",coursename);
        model.addAttribute("courseList",courseList);
        return "student/showCourse";
    }

    //执行选课操作
    @RequestMapping("/stuSelectedCourse")
    public String stuSelectedCourse(Integer id, HttpSession session) throws Exception {
        //获取当前登录学生的studentname(学生的编号)
        //User user= (User) session.getAttribute("user");

        //根据学生编号获取该学生已选的课程
        int studentId=Integer.parseInt(SystemUtils.getCurrentUserName());
        List<SelectCourse> selectCourseList=selectCourseService.findSelectCourse(studentId);
        for(SelectCourse selectCourse : selectCourseList){
            if(id==selectCourse.getCourseid()){
                //当前课程已经被选
                throw new CustomException("当前课程已经选了,请重新选择其他课程");
            }
        }
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setCourseid(id);
        selectCourse.setStudentid(studentId);
        selectCourseService.save(selectCourse);
        return "redirect:/student/selectedCourse";
    }

    //显示已选课程,并分页
    @RequestMapping("/selectedCourse")
    public String selectedCourse(Integer page, Model model,
                                 HttpSession session) throws UnsupportedEncodingException {
        Page p=SystemUtils.setPageNum(page);
        //获取当前登录学生的studentname(学生的编号)
        //User user= (User) session.getAttribute("user");
        int studentId=Integer.parseInt(SystemUtils.getCurrentUserName());

        //创建SelectCourse对象,并设置相关属性
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setStudentid(studentId);
        //设置Mark属性为0,表示查询正在学习的课程
        selectCourse.setMark(0);
        List<SelectCourseCustom> selectedCourseList=selectCourseService
                .findSelectCourseByPage(selectCourse,p);

        p.setTotalPage(selectCourseService.selectCount(selectCourse));
        //设置页面要显示的数据
        session.setAttribute("selectCourseCount",selectCourseService.selectCount(selectCourse));
        model.addAttribute("Page",p);
        model.addAttribute("selectedCourseList",selectedCourseList);
        return "student/selectedCourse";
    }


    //显示已修课程
    @RequestMapping("/overCourse")
    public String overCourse(Integer page, Model model,HttpSession session){
        Page p=SystemUtils.setPageNum(page);
        //获取当前登录学生的studentname(学生的编号)
        //User user= (User) session.getAttribute("user");
        int studentId=Integer.parseInt(SystemUtils.getCurrentUserName());

        //创建SelectCourse对象,并设置相关属性
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setStudentid(studentId);
        //设置Mark属性不为空,表示查询已修完的课程
        selectCourse.setMark(1);
        List<SelectCourseCustom> selectedCourseList=selectCourseService
                .findSelectCourseByPage(selectCourse,p);

        p.setTotalPage(selectCourseService.selectCount(selectCourse));
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        session.setAttribute("finishedCourseCount",selectCourseService.selectCount(selectCourse));
        model.addAttribute("selectedCourseList",selectedCourseList);
        return "student/overCourse";
    }


    //退课操作
    @RequestMapping("/outCourse")
    public String outCourse(HttpSession session,Integer courseId) throws Exception {
        if (courseId == null) {
            return "student/selectedCourse";
        }
        //获得当前用户
        //User user= (User) session.getAttribute("user");
        Subject user=SecurityUtils.getSubject();
        String studentName= (String) user.getPrincipal();
        selectCourseService.deleteSelectCourse(Integer.parseInt(studentName),courseId);
        return "redirect:/student/selectedCourse";
    }


    //修改密码页面
    @RequestMapping(value="/editPassword",method=RequestMethod.GET)
    public String editPasswordUI(){
        return "student/editPassword";
    }


}
