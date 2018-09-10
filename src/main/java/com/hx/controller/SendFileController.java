package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 15:53
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.config.HttpSend;
import com.hx.model.FileData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SendFileController {
    @RequestMapping("/sendFile")
    @ResponseBody
    public String sendFile(){
        //发送文件
        //HttpSend.sendHttp(file,Ip,Ipall,request);
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(date);
        FileData fileData=new FileData();
        fileData.setFilename("文件");
        fileData.setIp("172.16.107.205");
        fileData.setIpall("172.16.107.205");
        fileData.setSendTime(time);
        List<FileData> list=new ArrayList<FileData>();
        list.add(fileData);
        JSONObject jsonObject=new JSONObject();
        String json=jsonObject.toJSONString(list);
        //发送文件数据
        HttpSend.HttpPostWithJson("172.16.107.205",json);
        return "发送成功";
    }
    @RequestMapping(value="/receiveData")
    @ResponseBody
    public String receiveData(@RequestParam String json){
        System.out.println(json.toString());
        return json;
    }
}
