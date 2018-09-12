package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:59
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.model.Login;
import com.hx.model.Udpsendfile;
import com.hx.service.UdpsendfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UdpsendfileController {
    @Autowired
    private UdpsendfileService udpsendfileService;
    /**
     *
     * 功能描述: 分页查询发文统计列表
     *
     * @param: 
     * @return: 
     * @auther: 张立恒
     * @date: 2018/9/11 15:17
     */
    @RequestMapping(value="/selectUpdsendfile")
    public String selectUpdsendfile(Integer pageStart, Integer pageSize, HttpServletRequest request){
        HttpSession session = request.getSession();
        Login login=(Login)session.getAttribute("login");
        List<Udpsendfile> list=udpsendfileService.selectUpdsendfile(pageStart,pageSize,login.getId());
        String json=JSONObject.toJSONStringWithDateFormat(list,"yyyy-MM-dd HH:mm:ss");
        return json;
    }
}
