package com.hx.controller;


import com.hx.model.Udpsendfile;
import com.hx.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/21 16:18
 * @desc 发文总量
 */
@Controller
@RequestMapping("/hx")
public class DispatchController {
    @Autowired
    private DispatchService dispatchService;
    @RequestMapping("getAllUdpsendfile")
    public String dispatch(Model model){
        List<Udpsendfile> udpsendfile = dispatchService.dispatch();
        model.addAttribute("filetoaceptList",udpsendfile);
        return "udpsendfile";
    }
}
