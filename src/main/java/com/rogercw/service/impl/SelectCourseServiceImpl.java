package com.rogercw.service.impl;

import com.rogercw.mapper.SelectCourseMapper;
import com.rogercw.po.Course;
import com.rogercw.po.SelectCourse;
import com.rogercw.po.SelectCourseExample;
import com.rogercw.service.SelectCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 1 on 2018/5/30.
 */
@Service
@Transactional
public class SelectCourseServiceImpl implements SelectCourseService{

    @Resource
    private SelectCourseMapper selectCourseMapper;

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
}
