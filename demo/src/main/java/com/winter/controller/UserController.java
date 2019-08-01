package com.winter.controller;

import com.winter.model.User;
import com.winter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user){
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }

    @ResponseBody
    @RequestMapping(value = "/update", produces = {"application/json;charset=UTF-8"})
    public int updateUser(User user){
        return userService.updateByPrimaryKey(user);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", produces = {"application/json;charset=UTF-8"})
    public void deleteUser(User user){
        userService.deleteUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById/{userId}", produces = {"application/json;charset=UTF-8"})
    public void deleteUserById(@PathVariable("userId") Integer userId){
        userService.deleteUserById(userId);
    }

}