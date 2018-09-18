package com.hx.service.Impl;

import com.hx.model.Last;
import com.hx.model.Login;
import com.hx.service.UserService;
import com.hx.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/11 18:14
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    public Login login(String name, String password) {
        Login Login = userMapper.login(name,password);
        return Login;
    }

    public List<Login> selectRevUserList(Integer id) {
        return userMapper.selectRevUserList(id);
    }


}
