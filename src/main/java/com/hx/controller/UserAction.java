package com.hx.controller;

import com.hx.model.User;
import com.hx.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author 范聪敏
 * @date 2018/9/10 16:36
 * @desc
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Resource
    private UserService userService;



    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request , String name, String password, Model model){
		/*Map<String, String> map=new LinkedHashMap<String,String>();
 		map.put("name", user.getName());
		map.put("password", user.getPassword());*/

        User user = userService.login(name, password);
        if (user != null){
            System.out.println("用户登录："+name+password);
            HttpSession session = request.getSession();
            session.setAttribute("login", user);
            model.addAttribute("msg", "登录成功");
            return "success";
        }
        model.addAttribute("msg","登陆失败，用户名或密码错误");
        return "failed";
    }




}
