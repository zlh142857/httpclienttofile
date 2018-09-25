package com.hx.controller;

import com.hx.service.UdpsdelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping("uddel")
    public String udpsdelMany(int[] chk_value) {
        System.out.println(chk_value);
        if (chk_value == null ) {
            return "redirect:/selectUpdsendfile.do";
        }
        //直接传数组
        us.udpsdelMany(chk_value);
        //重定向
        return "redirect:/selectUpdsendfile.do";
    }
}
