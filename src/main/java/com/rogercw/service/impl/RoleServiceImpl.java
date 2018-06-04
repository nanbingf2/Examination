package com.rogercw.service.impl;

import com.rogercw.mapper.RoleMapper;
import com.rogercw.po.Role;
import com.rogercw.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 1 on 2018/6/4.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role findById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }
}
