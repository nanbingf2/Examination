package com.rogercw.po.custom;


import com.rogercw.po.Student;
import com.rogercw.po.custom.SelectCourseCustom;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 * Student类的扩展类
 */
public class StudentCustom extends Student {
    //所属院系名
    private String collegeName;

    //选修课集合
    private List<SelectCourseCustom> selectCourseList;

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<SelectCourseCustom> getSelectCourseList() {
        return selectCourseList;
    }

    public void setSelectCourseList(List<SelectCourseCustom> selectCourseList) {
        this.selectCourseList = selectCourseList;
    }
}
