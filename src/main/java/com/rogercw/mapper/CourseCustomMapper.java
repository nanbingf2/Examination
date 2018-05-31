package com.rogercw.mapper;

import com.rogercw.po.custom.CourseCustom;

import java.util.List;
import java.util.Map;

public interface CourseCustomMapper {

    public List<CourseCustom> findByPage(Map<String, Object> params);

}