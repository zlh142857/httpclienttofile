package com.hx.service;


import com.hx.model.User;

public interface UserService {
    //用户登录
    User login(String name, String password);
}
