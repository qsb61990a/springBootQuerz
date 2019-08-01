package com.winter.service;

import com.winter.model.User;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    public int updateByPrimaryKey(User user);

    public void deleteUser(User user);

    public void deleteUserById(Integer userId);

}