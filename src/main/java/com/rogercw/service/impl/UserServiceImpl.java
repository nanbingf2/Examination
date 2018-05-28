package com.rogercw.service.impl;

import com.rogercw.mapper.UserMapper;
import com.rogercw.po.User;
import com.rogercw.po.UserExample;
import com.rogercw.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 1 on 2018/5/28.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        User user=userMapper.selectByExample(userExample).get(0);
        return user;
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }
}
