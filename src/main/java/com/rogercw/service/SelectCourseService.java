package com.rogercw.service;

import com.rogercw.po.SelectCourse;

import java.util.List;

/**
 * Created by 1 on 2018/5/30.
 */
public interface SelectCourseService {

    public List<SelectCourse> findSelectCourse(int studentid);

    public void save(SelectCourse selectCourse);
}
