package com.hx.controller;

import com.hx.service.DelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 范聪敏
 * @date 2018/9/19 14:40
 * @desc
 */
@Controller
@RequestMapping("/hx")
public class DelController {
    @Autowired
    DelService delService;
    @RequestMapping("/del")
    public String deleteMany(int[] chk_value) {
        System.out.println(chk_value);
        if (chk_value == null ) {
            return "redirect:/hx/listmsg.do";
        }
        //直接传数组
        delService.deleteMany(chk_value);
        //重定向
        return "redirect:/hx/listmsg.do";
    }
}
