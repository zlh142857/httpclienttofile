package com.hx.controller;

import com.hx.service.UdpsdelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 范聪敏
 * @date 2018/9/19 16:26
 * @desc
 */
@Controller
@RequestMapping("/hx")

public class UdpsdelController {
    @Autowired
    UdpsdelService us;

    @RequestMapping("/uddel")
    @ResponseBody
    public String udpsdelMany(int[] num) {
        System.out.println(num);
        if (num == null ) {
            return "redirect:/hx/selectUpdsendfile.do";
        }else {

            //直接传数组
            us.udpsdelMany(num);
            //重定向
            return "/hx/selectUpdsendfile.do";
        }


    }






}
