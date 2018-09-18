package com.hx.service;


import com.hx.model.Login;

import java.util.List;

public interface UserService {
    //用户登录
    Login login(String name, String password);


    List<Login> selectRevUserList(Integer id);
}
