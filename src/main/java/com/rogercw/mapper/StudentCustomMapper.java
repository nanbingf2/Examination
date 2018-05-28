package com.rogercw.mapper;

import com.rogercw.po.custom.StudentCustom;
import com.rogercw.util.Page;

import java.util.List;

public interface StudentCustomMapper {

    public List<StudentCustom> findByPage(Page page);
}