package com.rogercw.service;

import com.rogercw.po.College;

import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
public interface CollegeService {

    /**
     * 查询所有院系
     * @return
     */
    public List<College> findAllCollege();
}
