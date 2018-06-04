package com.rogercw.service.impl;

import com.rogercw.mapper.CollegeMapper;
import com.rogercw.mapper.CourseCustomMapper;
import com.rogercw.mapper.CourseMapper;
import com.rogercw.po.College;
import com.rogercw.po.Course;
import com.rogercw.po.CourseExample;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.service.CourseService;
import com.rogercw.util.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/5/28.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseCustomMapper courseCustomMapper;
    @Resource
    private CollegeMapper collegeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CourseCustom> findByPage(Page page, CourseCustom course) {
        Map<String, Object> params = new HashMap<>();
        params.put("page", page);
        params.put("course", course);
        List<CourseCustom> courseList = courseCustomMapper.findByPage(params);
        return courseList;
    }

    @Override
    public void deleteById(int id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CourseCustom findById(int id) {
        Course course = courseMapper.selectByPrimaryKey(id);
        CourseCustom courseCustom = new CourseCustom();
        if (course != null) {
            //类拷贝
            BeanUtils.copyProperties(course, courseCustom);
            College college = collegeMapper.selectByPrimaryKey(course.getCollegeid());
            courseCustom.setCollegeName(college.getCollegename());
        }
        return courseCustom;
    }

    @Override
    public int findAllCount(Course course) {
        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseidIsNotNull();
        if (course != null) {
            if (course.getCoursename() != null && !course.getCoursename().equals("")) {
                criteria.andCoursenameLike("%"+course.getCoursename()+"%");
            }
            if (course.getTeacherid() != null && course.getTeacherid()!=0) {
                criteria.andTeacheridEqualTo(course.getTeacherid());
            }
        }
        return (int) courseMapper.countByExample(example);
    }

    @Override
    public void updateCourse(CourseCustom course) {
        courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public boolean save(Course course) {
        Course stu = courseMapper.selectByPrimaryKey(course.getCourseid());
        if (stu == null) {
            courseMapper.insert(course);
            return true;
        }
        //id在数据库中已经存在
        return false;
    }

}
