package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:59
 *@功能:
 */

import com.hx.service.UdpsendfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UdpsendfileController {
    @Autowired
    private UdpsendfileService udpsendfileService;
}
