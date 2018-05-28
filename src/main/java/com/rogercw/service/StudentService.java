package com.rogercw.service;

import com.rogercw.po.Student;
import com.rogercw.po.custom.StudentCustom;
import com.rogercw.util.Page;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
public interface StudentService {

    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<StudentCustom> findByPage(Page page);

    /**
     * 删除指定学生
     * @param id
     */
    public void deleteById(int id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Student findById(int id);

    /**
     * 查询所有学生数量
     * @return
     */
    public int findAllCount();

    /**
     * 更新学生信息
     * @param student
     */
    public void updateById(Student student);

    /**
     * 插入学生信息
     * @param student
     */
    public boolean save(Student student);

    /**
     * 根据学生姓名进行模糊查询
     * @param name
     * @return
     */
    public List<Student> findByName(String name);
}
