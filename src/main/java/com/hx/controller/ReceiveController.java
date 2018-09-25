package com.hx.controller;

import com.hx.model.Filetoacept;
import com.hx.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/20 16:10
 * @desc 接受总条数
 */
@Controller
@RequestMapping
    public class ReceiveController {

    @Autowired
    private ReceiveService receiveService;

    @RequestMapping("getAllfiletoacept")
    public String gross(Model model){
        List<Filetoacept> filetoacept = receiveService.findAll();
        model.addAttribute("filetoaceptList",filetoacept);
        return "filetoacept";
    }

























































































































}
