package com.hx.controller;

import com.hx.config.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/*接收文件*/
@Controller
public class HttpReceive {
    @RequestMapping("/httpReceive")
    public void httptwo(@RequestParam("file") MultipartFile file,HttpServletRequest request,
                        HttpServletResponse response){
        if(!file.isEmpty()){
            String fileName=file.getOriginalFilename();
            try {
                FileUtil.uploadFile(file.getBytes(), fileName);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print("成功了");
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("file是空的");
        }
    }
}
