package com.h5education.demo.service;

import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface UserService {
    //通过用户账号查询用户信息
    User selectUserInfo(String account);

    //用户注册
    Boolean insertUser(String account,String password);

    //用户登陆
    User loginUser(String account,String password);

    //修改用户信息
    User upDateUser(String account,String themplate,String name,String classes);

    //查询所有用户信息
    PageResult<User> selectAll(Integer page, Integer row);
}
