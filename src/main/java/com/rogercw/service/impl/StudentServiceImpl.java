package com.rogercw.service.impl;

import com.rogercw.mapper.CollegeMapper;
import com.rogercw.mapper.StudentMapper;
import com.rogercw.mapper.StudentCustomMapper;
import com.rogercw.po.College;
import com.rogercw.po.Student;
import com.rogercw.po.custom.StudentCustom;
import com.rogercw.po.StudentExample;
import com.rogercw.service.CollegeService;
import com.rogercw.service.StudentService;
import com.rogercw.util.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/5/28.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private StudentCustomMapper studentCustomMapper;
    @Resource
    private CollegeMapper collegeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<StudentCustom> findByPage(Page page,StudentCustom student) {
        Map<String,Object> params=new HashMap<>();
        params.put("page",page);
        params.put("student",student);
        List<StudentCustom> studentList = studentCustomMapper.findByPage(params);
        return studentList;
    }

    @Override
    public void deleteById(int id) {
        studentMapper.deleteByPrimaryKey(id);

    }

    @Override
    public StudentCustom findById(int id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        StudentCustom studentCustom=new StudentCustom();
        if (student != null) {
            //类拷贝
            BeanUtils.copyProperties(student,studentCustom);
            College college=collegeMapper.selectByPrimaryKey(student.getCollegeid());
            studentCustom.setCollegeName(college.getCollegename());
        }
        return studentCustom;
    }

    @Override
    public int findAllCount(Student student) {
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStudentidIsNotNull();
        if(student!=null){
            if (student.getStudentname() != null && !student.getStudentname().equals("")) {
                criteria.andStudentnameLike("%" + student.getStudentname() + "%");
            }
        }
        return (int) studentMapper.countByExample(example);
    }

    @Override
    public void updateStudent(StudentCustom student) {
        studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public boolean save(Student student) {
        Student stu=studentMapper.selectByPrimaryKey(student.getStudentid());
        if(stu==null){
            studentMapper.insert(student);
            return true;
        }
        //id在数据库中已经存在
        return false;
    }

    @Override
    public List<StudentCustom> findByName(String name) {
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStudentnameLike("%"+name+"%");
        List<Student> studentList=studentMapper.selectByExample(example);

        List<StudentCustom> result=new ArrayList<>();
        for (Student student:studentList){
            StudentCustom studentCustom=new StudentCustom();
            //类拷贝
            BeanUtils.copyProperties(student,studentCustom);
            //查询院系名称
            String collegeName=collegeMapper.selectByPrimaryKey(student.getCollegeid()).getCollegename();
            studentCustom.setCollegeName(collegeName);
            result.add(studentCustom);
        }
        return result;
    }
}
