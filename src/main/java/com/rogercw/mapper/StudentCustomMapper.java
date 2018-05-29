package com.rogercw.mapper;

import com.rogercw.po.custom.StudentCustom;
import com.rogercw.util.Page;

import java.util.List;
import java.util.Map;

public interface StudentCustomMapper {

    public List<StudentCustom> findByPage(Map<String,Object> params);
}