package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:57
 *@功能:
 */

import com.hx.model.Filetoacept;
import com.hx.model.Login;
import com.hx.service.FiletoaceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/hx")
public class FiletoaceptController {
    @Autowired
    private FiletoaceptService filetoaceptService;

    //获取收文列表
    @RequestMapping("/get/listmsg")
    @ResponseBody
    public List<Filetoacept> listFileToAceptMsg(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Login login=(Login)session.getAttribute("login");
        Integer receiverid=login.getId();
        List<Filetoacept> filetoacepts = filetoaceptService.listFileToAceptMsg(receiverid);
        if (filetoacepts != null){
            return filetoacepts;
        }
        return null;
    }

    //获取收文列表
    @RequestMapping("/get/mewListMsg")
    @ResponseBody
    public List<Filetoacept> getNewFiletoaceptList(@RequestParam(value = "receiverid",required = true) Integer receiverid, Model model){
        List<Filetoacept> filetoacepts = filetoaceptService.getUserUnReadFileList(receiverid,2);
        if (filetoacepts != null){
            return filetoacepts;
        }
        return null;
    }
}
