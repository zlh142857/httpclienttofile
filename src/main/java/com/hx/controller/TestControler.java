package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/18 9:58
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.config.Base;
import com.hx.config.HttpSend;
import com.hx.model.Filetoacept;
import com.hx.model.Last;
import com.hx.model.Login;
import com.hx.model.Udpsendfile;
import com.hx.service.FiletoaceptService;
import com.hx.service.SendFileService;
import com.hx.service.UdpsendfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TestControler {
    @Autowired
    private SendFileService sendFileService;
    @Autowired
    private UdpsendfileService udpsendfileService;
    @Autowired
    private FiletoaceptService filetoaceptService;

}
