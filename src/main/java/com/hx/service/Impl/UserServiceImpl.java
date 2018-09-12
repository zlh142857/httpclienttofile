package com.hx.service.Impl;

import com.hx.model.User;
import com.hx.service.UserService;
import com.hx.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范聪敏
 * @date 2018/9/11 18:14
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User login(String name, String password) {
        User user = userMapper.login(name,password);
        return null;
    }
}
