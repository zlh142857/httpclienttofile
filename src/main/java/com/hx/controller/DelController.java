package com.hx.controller;

import com.hx.service.DelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 范聪敏
 * @date 2018/9/19 14:40
 * @desc
 * DelController
 */
@Controller
@RequestMapping("/hx")
public class DelController {
    @Autowired
    DelService delService;

    @RequestMapping("/del")
    @ResponseBody
    public String deleteMany(int[] num) {
          System.out.println(num);
        if (num == null ) {
            return "redirect:/hx/listmsg.do";
        }else {

            //直接传数组
            delService.deleteMany(num);
            //重定向
            return "/hx/listmsg.do";
        }


    }
}
        