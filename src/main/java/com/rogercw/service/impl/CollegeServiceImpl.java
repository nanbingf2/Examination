package com.rogercw.service.impl;

import com.rogercw.mapper.CollegeMapper;
import com.rogercw.po.College;
import com.rogercw.po.CollegeExample;
import com.rogercw.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 1 on 2018/5/28.
 */
@Service
@Transactional
public class CollegeServiceImpl implements CollegeService{

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<College> findAllCollege() {
        CollegeExample example=new CollegeExample();
        CollegeExample.Criteria criteria=example.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(example);
    }
}
