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
        List<SelectCourse> selectCourseList=selectCourseService.findSelectCourse(Integer.parseInt(user.getUsername()));
        for(SelectCourse selectCourse : selectCourseList){
            if(id==selectCourse.getCourseid()){
                //当前课程已经被选
                throw new CustomException("当前课程已经选了,请重新选择其他课程");
            }
        }
        SelectCourse selectCourse=new SelectCourse();
        selectCourse.setCourseid(id);
        selectCourse.setStudentid(Integer.parseInt(user.getUsername()));
        selectCourseService.save(selectCourse);
        return "redirect:/student/selectedCourse";
    }

    //显示已选课程,并分页
    @RequestMapping("/selectedCourse")
    public String selectedCourse(Integer page, Model model,
                                 HttpSession session) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //获取当前登录学生的studentname(学生的编号)
        User user= (User) session.getAttribute("user");
        //根据学生编号获取该学生已选的课程
        List<SelectCourseCustom> courseList=selectCourseService
                .findSelectCourseByPage(Integer.parseInt(user.getUsername()),p);

        //对当前学生已选的课程进一步筛选(选出未修完的课程)
        List<SelectCourseCustom> selectedCourseList=new ArrayList<>();
        for(SelectCourseCustom selectCourseCustom : courseList){
            if (selectCourseCustom.getMark() == null) {
                //已选但未修完的课程
                selectCourseCustom.setOver(false);
                selectedCourseList.add(selectCourseCustom);
            }
        }

        p.setTotalPage(selectedCourseList.size());
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("selectedCourseList",selectedCourseList);
        return "student/selectedCourse";
    }


    //显示已修课程
    @RequestMapping("/overCourse")
    public String overCourse(Integer page, Model model,
                             HttpSession session){
        Page p=new Page();
        this.setPageNo(page,p);
        //获取当前登录学生的studentname(学生的编号)
        User user= (User) session.getAttribute("user");
        //根据学生编号获取该学生已选的课程
        List<SelectCourseCustom> courseList=selectCourseService
                .findSelectCourseByPage(Integer.parseInt(user.getUsername()),p);

        //对当前学生已选的课程进一步筛选(选出已修完的课程)
        List<SelectCourseCustom> selectedCourseList=new ArrayList<>();
        for(SelectCourseCustom selectCourseCustom : courseList){
            if (selectCourseCustom.getMark() != null) {
                //已选但未修完的课程
                selectCourseCustom.setOver(true);
                selectedCourseList.add(selectCourseCustom);
            }
        }

        p.setTotalPage(selectedCourseList.size());
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("selectedCourseList",selectedCourseList);
        return "student/overCourse";
    }


    //退课操作
    @RequestMapping("/outCourse")
    public String outCourse(HttpSession session,Integer courseId) throws Exception {
        if (courseId == null) {
            return "student/selectedCourse";
        }
        User user= (User) session.getAttribute("user");
        selectCourseService.deleteSelectCourse(Integer.parseInt(user.getUsername()),courseId);
        return "redirect:/student/selectedCourse";
    }


    //修改密码页面
    @RequestMapping(value="/editPassword",method=RequestMethod.GET)
    public String editPasswordUI(){
        return "student/editPassword";
    }

    //修改密码操作
    @RequestMapping(value = "/editPassword",method = RequestMethod.POST)
    public String editPassword(String password,String password1,
                               HttpSession session,Model model){
        //从session中获取当前用户的密码
        User user= (User) session.getAttribute("user");
        String pwd=user.getPassword();
        if(!pwd.equals(password)){
            //输入的旧密码与当前用户的密码错误
            String message="输入的密码错误";
            model.addAttribute("message",message);
            return "admin/editPassword";
        }
        //重新设置密码并更行当前用户
        user.setPassword(password1);
        userService.updateUser(user);
        session.setAttribute("user",user);
        return "student/editPassword";
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
