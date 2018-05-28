package com.rogercw.service.impl;

import com.rogercw.mapper.StudentMapper;
import com.rogercw.mapper.StudentCustomMapper;
import com.rogercw.po.Student;
import com.rogercw.po.custom.StudentCustom;
import com.rogercw.po.StudentExample;
import com.rogercw.service.StudentService;
import com.rogercw.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Transactional(readOnly = true)
    @Override
    public List<StudentCustom> findByPage(Page page) {
        List<StudentCustom> studentList = studentCustomMapper.findByPage(page);
        return studentList;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public int findAllCount() {
        StudentExample example=new StudentExample();
        StudentExample.Criteria criteria=example.createCriteria();
        criteria.andStudentidIsNotNull();
        return (int) studentMapper.countByExample(example);
    }

    @Override
    public void updateById(Student student) {

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
    public List<Student> findByName(String name) {
        return null;
    }
}
