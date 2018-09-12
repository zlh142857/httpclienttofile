package com.hx.dao;

import com.hx.model.User;
import org.apache.ibatis.annotations.Param;



/**
 * @author 范聪敏
 * @date 2018/9/10 15:42
 * @desc
 */
public interface UserMapper {
    //根据用户名和密码查询用户
    //注解的两个参数会自动封装成map集合，括号内即为键
    User findUserByNameAndPwd(@Param("name")String name, @Param("password")String password);


    //登陆
    User login(@Param(value = "name") String name,@Param(value = "password") String password);
}
