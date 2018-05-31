package com.rogercw.service.impl;

import com.rogercw.mapper.SelectCourseCustomMapper;
import com.rogercw.mapper.SelectCourseMapper;
import com.rogercw.po.Course;
import com.rogercw.po.SelectCourse;
import com.rogercw.po.SelectCourseExample;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.SelectCourseCustom;
import com.rogercw.service.SelectCourseService;
import com.rogercw.util.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/5/30.
 */
@Service
@Transactional
public class SelectCourseServiceImpl implements SelectCourseService{

    @Resource
    private SelectCourseMapper selectCourseMapper;
    @Resource
    private SelectCourseCustomMapper selectCourseCustomMapper;

    @Override
    public List<SelectCourse> findSelectCourse(int studentid) {
        SelectCourseExample example=new SelectCourseExample();
        SelectCourseExample.Criteria criteria=example.createCriteria();
        criteria.andStudentidEqualTo(studentid);
        List<SelectCourse> selectCourses = selectCourseMapper.selectByExample(example);
        return selectCourses;
    }

    @Override
    public void save(SelectCourse selectCourse) {
        selectCourseMapper.insert(selectCourse);
    }

    @Override
    public List<SelectCourseCustom> findSelectCourseByPage(int studentId, Page page) {
        Map<String,Object> params=new HashMap<>();
        params.put("studentId",studentId);
        params.put("page",page);
        List<SelectCourseCustom> courseList = selectCourseCustomMapper.findSelectCourseByPage(params);
        return courseList;
    }

    @Override
    public int selectCount(int studentId) {
        return selectCourseCustomMapper.selectCount(studentId);
    }

    @Override
    public void deleteSelectCourse(int studentId,int courseId) {
        SelectCourseExample example=new SelectCourseExample();
        SelectCourseExample.Criteria criteria=example.createCriteria();
        criteria.andStudentidEqualTo(studentId);
        criteria.andCourseidEqualTo(courseId);
        selectCourseMapper.deleteByExample(example);
    }


    @Override
    public List<SelectCourseCustom> findGradeByCourseId(Page page, int courseId) {
        Map params=new HashMap();
        params.put("page",page);
        params.put("courseid",courseId);
        return selectCourseCustomMapper.findGradeByCourseId(params);
    }

    @Override
    public void updateSelectCourse(SelectCourseCustom selectCourseCustom) {
        SelectCourseExample example=new SelectCourseExample();
        SelectCourseExample.Criteria criteria=example.createCriteria();
        criteria.andCourseidEqualTo(selectCourseCustom.getCourseid());
        criteria.andStudentidEqualTo(selectCourseCustom.getStudentid());
        selectCourseMapper.updateByExample(selectCourseCustom,example);
    }
}
