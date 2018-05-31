package com.rogercw.mapper;

import com.rogercw.po.Course;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.SelectCourseCustom;
import com.rogercw.po.custom.StudentCustom;

import java.util.List;
import java.util.Map;

public interface SelectCourseCustomMapper {

    public List<SelectCourseCustom> findSelectCourseByPage(Map<String, Object> params);

    public Integer selectCount(int studentId);

    public List<SelectCourseCustom> findGradeByCourseId(Map params);
}