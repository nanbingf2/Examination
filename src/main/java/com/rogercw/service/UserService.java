package com.rogercw.service;

import com.rogercw.po.User;

/**
 * Created by 1 on 2018/5/28.
 */
public interface UserService {

    public User findUserByUserNameAndPassword(String username, String password);

    public void save(User user);

    public void deleteByName(String id);
}
