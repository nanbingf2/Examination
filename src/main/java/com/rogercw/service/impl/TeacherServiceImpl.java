package com.rogercw.service.impl;

import com.rogercw.mapper.CollegeMapper;
import com.rogercw.mapper.TeacherCustomMapper;
import com.rogercw.mapper.TeacherMapper;
import com.rogercw.po.College;
import com.rogercw.po.Teacher;
import com.rogercw.po.TeacherExample;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.TeacherCustom;
import com.rogercw.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private TeacherCustomMapper teacherCustomMapper;
    @Resource
    private CollegeMapper collegeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TeacherCustom> findByPage(Page page, TeacherCustom teacher) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("teacher", teacher);
        List<TeacherCustom> teacherList = teacherCustomMapper.findByPage(params);
        return teacherList;
    }

    @Override
    public void deleteById(int id) {
        teacherMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TeacherCustom findById(int id) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        TeacherCustom teacherCustom = new TeacherCustom();
        if (teacher != null) {
            //类拷贝
            BeanUtils.copyProperties(teacher, teacherCustom);
            College college = collegeMapper.selectByPrimaryKey(teacher.getCollegeid());
            teacherCustom.setCollegeName(college.getCollegename());
        }
        return teacherCustom;
    }

    @Override
    public int findAllCount() {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andTeacheridIsNotNull();
        return (int) teacherMapper.countByExample(example);
    }

    @Override
    public void updateTeacher(TeacherCustom teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public boolean save(Teacher teacher) {
        Teacher stu = teacherMapper.selectByPrimaryKey(teacher.getTeacherid());
        if (stu == null) {
            teacherMapper.insert(teacher);
            return true;
        }
        //id在数据库中已经存在
        return false;
    }

    @Override
    public List<TeacherCustom> findAll() {
        TeacherExample example=new TeacherExample();
        TeacherExample.Criteria criteria=example.createCriteria();
        criteria.andTeacheridIsNotNull();
        List<Teacher> teacherList = teacherMapper.selectByExample(example);

        List<TeacherCustom> teacherCustomList=null;
        if(teacherList!=null){
            teacherCustomList=new ArrayList<>();
            for (Teacher teacher : teacherList) {
                TeacherCustom teacherCustom=new TeacherCustom();
                //类拷贝
                BeanUtils.copyProperties(teacher,teacherCustom);
                teacherCustomList.add(teacherCustom);
            }

        }
        return teacherCustomList;
    }

}
