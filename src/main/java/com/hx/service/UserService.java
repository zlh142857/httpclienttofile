package com.hx.service;


import com.hx.model.Login;

public interface UserService {
    //用户登录
    Login login(String name, String password);


}
