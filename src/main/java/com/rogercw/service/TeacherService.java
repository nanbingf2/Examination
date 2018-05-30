package com.rogercw.service;

import com.rogercw.po.Teacher;
import com.rogercw.po.custom.TeacherCustom;
import com.rogercw.util.Page;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
public interface TeacherService {

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<TeacherCustom> findByPage(Page page, TeacherCustom teacher);

    /**
     * 删除指定教师
     * @param id
     */
    public void deleteById(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public TeacherCustom findById(int id);

    /**
     * 查询所有教师数量
     * @return
     */
    public int findAllCount();

    /**
     * 更新教师信息
     * @param teacher
     */
    public void updateTeacher(TeacherCustom teacher);

    /**
     * 插入教师信息
     * @param teacher
     */
    public boolean save(Teacher teacher);

    public List<TeacherCustom> findAll();

}
