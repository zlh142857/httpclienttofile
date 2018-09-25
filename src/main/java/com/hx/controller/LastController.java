package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:56
 *@功能:
 */

import com.hx.service.LastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LastController {
    @Autowired

    private LastService lastService;
}
