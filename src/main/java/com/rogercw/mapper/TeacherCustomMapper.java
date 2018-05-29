package com.rogercw.mapper;

import com.rogercw.po.custom.TeacherCustom;

import java.util.List;
import java.util.Map;

public interface TeacherCustomMapper {

    public List<TeacherCustom> findByPage(Map<String, Object> params);
}