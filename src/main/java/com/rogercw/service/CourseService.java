package com.rogercw.service;

import com.rogercw.po.Course;
import com.rogercw.po.custom.CourseCustom;
import com.rogercw.util.Page;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
public interface CourseService {

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<CourseCustom> findByPage(Page page, CourseCustom course);

    /**
     * 删除指定课程
     * @param id
     */
    public void deleteById(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public CourseCustom findById(int id);

    /**
     * 查询所有课程数量
     * @return
     */
    public int findAllCount();

    /**
     * 更新课程信息
     * @param course
     */
    public void updateCourse(CourseCustom course);

    /**
     * 插入课程信息
     * @param course
     */
    public boolean save(Course course);

}
