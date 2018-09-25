package com.hx.dao;

import com.hx.model.Login;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 范聪敏
 * @date 2018/9/10 15:42
 * @desc
 */
public interface UserMapper {
    //根据用户名和密码查询用户
    //注解的两个参数会自动封装成map集合，括号内即为键
    Login findUserByNameAndPwd(@Param("name")String name, @Param("password")String password);


    //登陆
    Login login(@Param(value = "name") String name,@Param(value = "password") String password);

    List<Login> selectRevUserList(@Param("id") Integer id);
}
