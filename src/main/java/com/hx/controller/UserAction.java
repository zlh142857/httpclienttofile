package com.hx.controller;

import com.alibaba.fastjson.JSONObject;
import com.hx.model.Login;
import com.hx.model.Last;

import com.hx.service.LastService;
import com.hx.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;


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
    @Resource
    private LastService lastService;


    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request, String name, String password) {
        Login login = userService.login(name, password);
        if (login != null) {
            System.out.println("用户登录：" + name + password);
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("str", "success");
            loginInfoLog(login);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("str", "failed");
        return "successCallBack(" + jsonObject.toJSONString() + ")";
    }

    /**
     * 登陆日志记录
     * @param login
     */
    private void loginInfoLog(Login login) {
        String SERVER_IP = null;
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                SERVER_IP = ip.getHostAddress();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    SERVER_IP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Last last = new Last();
        last.setUserid(login.getId());
        last.setLanding_time(new Date());
        last.setUsername(login.getName());
        last.setTerritory_ip(SERVER_IP);
        lastService.insertLoginLog(last);
    }








}
