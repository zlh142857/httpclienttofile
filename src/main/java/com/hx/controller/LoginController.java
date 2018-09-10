package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:52
 *@功能:
 */

import com.hx.model.Login;
import com.hx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

}
