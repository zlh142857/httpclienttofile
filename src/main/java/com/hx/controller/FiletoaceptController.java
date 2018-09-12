package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:57
 *@功能:
 */

import com.hx.model.Filetoacept;
import com.hx.service.FiletoaceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hx")
public class FiletoaceptController {
    @Autowired
    private FiletoaceptService filetoaceptService;

    //获取收文列表
    @RequestMapping("/get/listmsg")
    @ResponseBody
    public List<Filetoacept> listFileToAceptMsg(@RequestParam(value = "receiverid",required = true) Integer receiverid, Model model){
        List<Filetoacept> filetoacepts = filetoaceptService.listFileToAceptMsg(receiverid);
        if (filetoacepts != null){
            return filetoacepts;
        }
        return null;
    }
}
