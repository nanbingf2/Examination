package com.rogercw.po.custom;


import com.rogercw.po.Course;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 * Course类的扩展类
 */
public class CourseCustom extends Course {
    //所属院系名
    private String collegeName;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
