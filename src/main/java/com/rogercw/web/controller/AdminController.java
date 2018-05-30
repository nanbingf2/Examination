package com.rogercw.web.controller;

import com.rogercw.exception.CustomException;
import com.rogercw.po.College;
import com.rogercw.po.Student;
import com.rogercw.po.Teacher;
import com.rogercw.po.User;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.StudentCustom;
import com.rogercw.po.custom.TeacherCustom;
import com.rogercw.service.*;
import com.rogercw.util.CodingUtil;
import com.rogercw.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    @Resource
    private TeacherService teacherService;
    @Resource
    private CourseService courseService;

    @RequestMapping("/showStudent")
    public String showStudent(Integer page,Model model, StudentCustom student) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //解决get方式乱码问题
        String studentname=CodingUtil.encode(student.getStudentname());
        student.setStudentname(studentname);
        List<StudentCustom> studentList=studentService.findByPage(p,student);
        int count=studentService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("studentList",studentList);
        System.out.println(studentname);
        model.addAttribute("studentname",studentname);
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



    /*@RequestMapping("/findStudentByName")
    public String findStudentByName(String studentName,int pageNo,Model model){
        List<StudentCustom> studentList = studentService.findByName(studentName);
        model.addAttribute("studentList",studentList);
        return "admin/showStudent";
    }*/


    @RequestMapping(value = "/editStudent",method = RequestMethod.GET)
    public String editStudentUI(Model model,Integer id) throws Exception {
        if (id == null) {
            return "redirect:/admin/showStudent";
        }
        //查询当前学生,以便数据回显
        StudentCustom student = studentService.findById(id);
        if (student == null) {
            //没有查到学生
            throw new Exception();
        }

        //查询所有院系,供管理员选择
        List<College> collegeList=collegeService.findAllCollege();
        model.addAttribute("student",student);
        model.addAttribute("collegeList",collegeList);
        return "admin/editStudent";
    }


    @RequestMapping(value = "/editStudent",method = RequestMethod.POST)
    public String editStudent(StudentCustom studentCustom){
        studentService.updateStudent(studentCustom);
        //重定向到分页查询页面
        return "redirect:/admin/showStudent";
    }

    @RequestMapping("/deleteStudent")
    public String deleteStudent(Integer id){
        if (id == null) {
            return "admin/showStudent";
        }
        //在学生表中删除记录
        studentService.deleteById(id);
        //在用户表中删除记录,用户表中的姓名为学生id
        userService.deleteByName(id.toString());

        return "redirect:/admin/showStudent";
    }


    //*******************************教师管理************************************

    @RequestMapping("showTeacher")
    public String showStudent(Integer page,Model model, TeacherCustom teacher) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //解决get方式乱码问题
        String teachername=CodingUtil.encode(teacher.getTeachername());
        teacher.setTeachername(teachername);
        List<TeacherCustom> teacherList=teacherService.findByPage(p,teacher);
        int count=teacherService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("teacherList",teacherList);
        model.addAttribute("teachername",teachername);
        return "admin/showTeacher";
    }

    @RequestMapping(value = "/addTeacher",method = RequestMethod.GET)
    public String addTeacherUI(Model model){
        //查询所有院系,以便用户选择
        List<College> collegeList=collegeService.findAllCollege();
        model.addAttribute("collegeList",collegeList);
        return "admin/addTeacher";
    }

    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    public String addTeacher(TeacherCustom teacherCustom, Model model){
        boolean flag=teacherService.save(teacherCustom);
        if(!flag){
            //数据库中已经存在该id
            model.addAttribute("message","教师编号已经存在,请重新输入");
            return "error";
        }
        //在学生表中保存成功
        //同时将该学生注册到用户表中,
        User user=new User();
        user.setUsername(teacherCustom.getTeacherid().toString());
        user.setPassword("123");
        user.setRole(2);
        userService.save(user);
        return "redirect:/admin/showTeacher";
    }

    @RequestMapping(value = "/editTeacher",method = RequestMethod.GET)
    public String editTeacherUI(Model model,Integer id) throws Exception {
        if (id == null) {
            return "redirect:/admin/showTeacher";
        }
        //查询当前学生,以便数据回显
        TeacherCustom teacher = teacherService.findById(id);
        if (teacher == null) {
            //没有查到学生
            throw new Exception();
        }

        //查询所有院系,供管理员选择
        List<College> collegeList=collegeService.findAllCollege();
        model.addAttribute("teacher",teacher);
        model.addAttribute("collegeList",collegeList);
        return "admin/editTeacher";
    }


    @RequestMapping(value = "/editTeacher",method = RequestMethod.POST)
    public String editTeacher(TeacherCustom teacherCustom){
        teacherService.updateTeacher(teacherCustom);
        //重定向到分页查询页面
        return "redirect:/admin/showTeacher";
    }

    @RequestMapping("/deleteTeacher")
    public String deleteTeacher(Integer id){
        if (id == null) {
            return "admin/showTeacher";
        }
        //在学生表中删除记录
        teacherService.deleteById(id);
        //在用户表中删除记录,用户表中的姓名为学生id
        userService.deleteByName(id.toString());

        return "redirect:/admin/showTeacher";
    }


    //***************************课程管理*******************************
    @RequestMapping("showCourse")
    public String showCourse(Integer page, Model model, CourseCustom course) throws UnsupportedEncodingException {
        Page p=new Page();
        this.setPageNo(page,p);
        //解决get方式乱码问题
        String coursename=CodingUtil.encode(course.getCoursename());
        course.setCoursename(coursename);
        List<CourseCustom> courseList=courseService.findByPage(p,course);
        int count=courseService.findAllCount();
        p.setTotalPage(count);
        //设置页面要显示的数据
        model.addAttribute("Page",p);
        model.addAttribute("courseList",courseList);
        model.addAttribute("coursename",coursename);
        return "admin/showCourse";
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.GET)
    public String addCourseUI(Model model){
        //查询所有院系与老师,以便用户选择
        List<College> collegeList=collegeService.findAllCollege();
        List<TeacherCustom> teacherList=teacherService.findAll();
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("teacherList",teacherList);
        return "admin/addCourse";
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.POST)
    public String addCourse(CourseCustom courseCustom, Model model){
        boolean flag=courseService.save(courseCustom);
        if(!flag){
            //数据库中已经存在该id
            model.addAttribute("message","课程编号已经存在,请重新输入");
            return "error";
        }
        return "redirect:/admin/showCourse";
    }

    @RequestMapping(value = "/editCourse",method = RequestMethod.GET)
    public String editCourseUI(Model model,Integer id) throws Exception {
        if (id == null) {
            return "redirect:/admin/showCourse";
        }
        //查询当前课程信息,以便数据回显
        CourseCustom course = courseService.findById(id);
        if (course == null) {
            //没有查到学生
            throw new Exception();
        }

        //查询所有院系与教师,供管理员选择
        List<College> collegeList=collegeService.findAllCollege();
        List<TeacherCustom> teacherList=teacherService.findAll();
        model.addAttribute("course",course);
        model.addAttribute("collegeList",collegeList);
        model.addAttribute("teacherList",teacherList);
        return "admin/editCourse";
    }


    @RequestMapping(value = "/editCourse",method = RequestMethod.POST)
    public String editCourse(CourseCustom courseCustom){
        courseService.updateCourse(courseCustom);
        //重定向到分页查询页面
        return "redirect:/admin/showCourse";
    }

    @RequestMapping("/deleteCourse")
    public String deleteCourse(Integer id){
        if (id == null) {
            return "admin/showCourse";
        }
        //在学生表中删除记录
        courseService.deleteById(id);

        return "redirect:/admin/showCourse";
    }



    //***************************个人信息操作***************************
    @RequestMapping(value = "/editPassword",method = RequestMethod.GET)
    public String editPasswordUI(){
        return "admin/editPassword";
    }

    @RequestMapping(value = "/editPassword",method = RequestMethod.POST)
    public String editPassword(String password, String password1,
                                HttpSession session,Model model){
        //从session中获取当前用户的密码
        User user= (User) session.getAttribute("user");
        String pwd=user.getPassword();
        if(!password.equals(pwd)){
            //输入的旧密码与当前用户的密码错误
            String message="输入的密码错误";
            model.addAttribute("message",message);
            return "admin/editPassword";
        }
        //重新设置密码并更行当前用户
        user.setPassword(password1);
        userService.updateUser(user);
        session.setAttribute("user",user);
        return "admin/editPassword";
    }


    @RequestMapping("/resetPassword")
    public String resetPassword(Integer id) throws Exception {
        User u=userService.findByUserName(id.toString());
        if (u==null){
            throw new CustomException("指定用户不存在");
        }
        //重置密码并更新用户
        u.setPassword("123");
        userService.updateUser(u);
        return "redirect:/admin/showStudent";
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
