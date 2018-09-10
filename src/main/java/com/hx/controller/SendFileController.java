package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 15:53
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.config.FileUtil;
import com.hx.config.HttpSend;
import com.hx.model.FileData;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        //发送文件
        //HttpSend.sendHttp(file,Ipall,request);
        //发送文件数据
        String re=HttpSend.HttpPostWithJson("172.16.107.205",json);
        return re;
    }
    /**
     *
     * 功能描述:接收文件的其他数据:IP
     *
     * @param: 
     * @return: 
     * @auther: 张立恒
     * @date: 2018/9/10 18:04
     */
    @RequestMapping(value="/receiveData")
    @ResponseBody
    public void receiveData(HttpServletRequest request,HttpServletResponse response){
        try {
            InputStream in=request.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
            //将数据存进数据库
            if(str!=""){
                String rs="接收成功";
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print(rs);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * 功能描述:接收文件
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/10 18:04
     */
    @RequestMapping("/httpReceive")
    @ResponseBody
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
