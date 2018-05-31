package com.rogercw.service;

import com.rogercw.po.SelectCourse;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.po.custom.SelectCourseCustom;
import com.rogercw.util.Page;

import java.util.List;

/**
 * Created by 1 on 2018/5/30.
 */
public interface SelectCourseService {

    public List<SelectCourse> findSelectCourse(int studentid);

    public void save(SelectCourse selectCourse);

    public List<SelectCourseCustom> findSelectCourseByPage(int studentId, Page page);

    public int selectCount(int studentId);

    public void deleteSelectCourse(int studentId,int courseId);

    public List<SelectCourseCustom> findGradeByCourseId(Page page,int courseId);

    public void updateSelectCourse(SelectCourseCustom selectCourseCustom);
}
