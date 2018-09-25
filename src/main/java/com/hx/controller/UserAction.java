package com.hx.controller;

import com.alibaba.fastjson.JSONObject;
import com.hx.model.Login;
import com.hx.model.Last;

import com.hx.model.Udpsendfile;
import com.hx.service.FiletoaceptService;
import com.hx.service.LastService;
import com.hx.service.UdpsendfileService;
import com.hx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


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
    @Autowired
    private UdpsendfileService udpsendfileService;
    @Autowired
    private FiletoaceptService filetoaceptService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Login login(HttpServletRequest request, String name, String password) {
        Login login = userService.login(name, password);
        if (login != null) {
            String ip=this.loginInfoLog();
            Last last = new Last();
            Date date=new Date();
            last.setUserid(login.getId());
            last.setLanding_time(date);
            last.setUsername(login.getName());
            last.setTerritory_ip(ip);
            lastService.insertLoginLog(last);
            //查询发文收文次数
            Integer sendCount=udpsendfileService.selectSendCount(login.getId());
            Integer reCount=filetoaceptService.selectReCount(login.getId());
            login.setIp(ip);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time=sdf.format(date);
            login.setTime(time);
            login.setSendCount(sendCount);
            login.setReCount(reCount);
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            return login;
        }
        return null;
    }

    /**
     * 登陆日志记录
     * @param
     */
    private String loginInfoLog() {
        Enumeration<NetworkInterface> nis;
        String ip = null;
        try {
            nis = NetworkInterface.getNetworkInterfaces();
            for (; nis.hasMoreElements();) {
                NetworkInterface ni = nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                for (; ias.hasMoreElements();) {
                    InetAddress ia = ias.nextElement();
                    //ia instanceof Inet6Address && !ia.equals("")
                    if (ia instanceof Inet4Address && !ia.getHostAddress().equals("127.0.0.1")) {
                        ip = ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ip;
    }
    /**
     *
     * 功能描述:查询下拉列表框
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/14 19:47
     */
    @RequestMapping(value="/selectRevUserList")
    @ResponseBody
    public String selectRevUserList(HttpServletRequest request){
        HttpSession session = request.getSession();
        Login login=(Login)request.getSession().getAttribute("login");
        session.setAttribute("login",login);
        List<Login> list=userService.selectRevUserList(login.getId());
        String json=JSONObject.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss");
        return json;
    }






}
