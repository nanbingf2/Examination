package com.rogercw.po.custom;

import com.rogercw.po.SelectCourse;

/**
 * Created by 1 on 2018/5/28.
 */
public class SelectCourseCustom extends SelectCourse {

    private CourseCustom courseCustom;
    private String studentname;
    private boolean over=false;//是否已修完该课程,默认未修完

    public CourseCustom getCourseCustom() {
        return courseCustom;
    }

    public void setCourseCustom(CourseCustom courseCustom) {
        this.courseCustom = courseCustom;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
