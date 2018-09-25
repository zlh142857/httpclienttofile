package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 15:53
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.config.Base;
import com.hx.config.FileUtil;
import com.hx.config.HttpSend;
import com.hx.model.*;
import com.hx.service.FiletoaceptService;
import com.hx.service.SendFileService;
import com.hx.service.UdpsendfileService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SendFileController {
    @Autowired
    private SendFileService sendFileService;
    @Autowired
    private UdpsendfileService udpsendfileService;
    @Autowired
    private FiletoaceptService filetoaceptService;
    /**
     *
     * 功能描述: 发送一个文件给多个用户
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/18 16:38
     */
    @RequestMapping(value="/pdfFile")
    @ResponseBody
    public String file(HttpServletRequest request,String titles,String userids,String filename) throws IOException {
        JSONObject jsonObject = new JSONObject();
        String reStr = "";
        String fileName="";
        if(titles.isEmpty()){
            reStr="请填写文件标题";
            jsonObject.put("str", reStr);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
        }else{
            fileName=titles+".pdf";
            try {
                //解码转换后转换成InputStream
                InputStream inputStream=Base.base64(filename);
                try{
                    String ustr=userids.replace("[","");
                    String ustr2=ustr.replace("]","");
                    String [] str=ustr2.split(",");
                    int sizeIds=str.length;
                    for (int i = 0; i < sizeIds; i++) {
                        //根据userID查询每个用户对应的最后登录的两个IP
                        Last last = sendFileService.selectIpsByUserid(Integer.valueOf(str[i]));
                        if(last == null){
                            reStr="您选择的收件人不存在";
                            jsonObject.put("str", reStr);
                            return "successCallBack(" + jsonObject.toJSONString() + ")";
                        }else{
                            String sendRe=HttpSend.newSendFile(last.getTerritory_ip(),fileName,inputStream);
                            if (!sendRe.equals("发送成功")) {
                                reStr="发送文件失败";
                                jsonObject.put("str", reStr);
                                return "successCallBack(" + jsonObject.toJSONString() + ")";
                            }else{
                                HttpSession session = request.getSession();
                                Login login=(Login)session.getAttribute("login");
                                //将获取到的文件数据发送到工具类
                                String username = last.getUsername();
                                String Ipall = last.getTerritory_ip();
                                Integer userid = last.getUserid();
                                Date date = new Date();
                                Udpsendfile udpsendfile = new Udpsendfile();
                                udpsendfile.setFilename(fileName);
                                udpsendfile.setReceiverid(userid);
                                udpsendfile.setRecipient(username);
                                udpsendfile.setPosttime(date);
                                udpsendfile.setUserid(login.getId());
                                //将发送的数据存放到发文统计表
                                int count = udpsendfileService.insertUdpsendfile(udpsendfile);
                                if (count != 1) {
                                    reStr = "没有记录到发文统计";
                                    jsonObject.put("str", reStr);
                                    return "successCallBack(" + jsonObject.toJSONString() + ")";
                                }else{
                                    //将发送的数据添加到工具类,从session获取发送人的id
                                    //String filepath="/usr/uploadImage/"+fileName;
                                    //根据接收人的id查询接收人的姓名
                                    String filepath = "D:\\imageTest\\" + fileName;
                                    Filetoacept filetoacept = new Filetoacept();
                                    filetoacept.setDispatcher_id(login.getId());
                                    filetoacept.setFilename(fileName);
                                    filetoacept.setReceiver_id(last.getUserid());
                                    filetoacept.setFilesaving(filepath);
                                    filetoacept.setReadunread((byte) 2);
                                    List<Filetoacept> list = new ArrayList<Filetoacept>();
                                    list.add(filetoacept);
                                    String json = jsonObject.toJSONString(list);
                                    String jsonRe = HttpSend.HttpPostWithJson(Ipall, json);
                                    if (!jsonRe.equals("发送成功")) {
                                        reStr = "接收数据失败";
                                        jsonObject.put("str", reStr);
                                        return "successCallBack(" + jsonObject.toJSONString() + ")";
                                    }
                                }
                            }
                        }
                    }
                }catch (NullPointerException n){
                    reStr="请选择接收人";
                    jsonObject.put("str", reStr);
                    return "successCallBack(" + jsonObject.toJSONString() + ")";
                }
                reStr = "发送成功";
                jsonObject.put("str", reStr);
                return "successCallBack(" + jsonObject.toJSONString() + ")";
            } catch (IOException e) {
                e.printStackTrace();
                reStr ="获取文件异常";
                jsonObject.put("str", reStr);
                return "successCallBack(" + jsonObject.toJSONString() + ")";
            }
        }
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
            //将数据存进数据库,接收文件统计列表
            List<Filetoacept> filetoacept= (List<Filetoacept>)JSONArray.toCollection(JSONArray.fromObject(str), Filetoacept.class);
            for (Filetoacept ftc:filetoacept){
                filetoaceptService.insertFtc(ftc);
            }
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
    /**
     *
     * 功能描述: 发送多个文件给同一个用户
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/12 10:42
     */
    @RequestMapping("/sendManyFile")
    @ResponseBody
    public String sendManyFile(@RequestParam("file") MultipartFile [] files,String userids,HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String reStr = "";
        if(files.length!=0){
            int sizeFile=files.length;
            try{
                String[] str = userids.split(",");
                int sizeIds=str.length;
                for(int i=0;i<sizeFile;i++){
                    for(int j=0;j<sizeIds;j++){
                        //根据userID查询每个用户对应的最后登录的两个IP
                        Last last = sendFileService.selectIpsByUserid(Integer.valueOf(str[j]));
                        if (last == null) {
                            reStr = "请选择有效的接收人";
                            jsonObject.put("str", reStr);
                            return "successCallBack(" + jsonObject.toJSONString() + ")";
                        }else{
                            //将获取到的file和两个IP发送到工具类
                            String sendRe = HttpSend.sendHttp(files[i], last.getTerritory_ip(), request);
                            if (sendRe.equals("失败")) {
                                reStr = "发送文件失败";
                                jsonObject.put("str", reStr);
                                return "successCallBack(" + jsonObject.toJSONString() + ")";
                            }else{
                                HttpSession session = request.getSession();
                                Login login=(Login)request.getSession().getAttribute("login");
                                //将获取到的文件数据发送到工具类
                                String filename = files[i].getOriginalFilename();
                                String username = last.getUsername();
                                String Ipall = last.getTerritory_ip();
                                Integer userid = last.getUserid();
                                Date date = new Date();
                                Udpsendfile udpsendfile = new Udpsendfile();
                                udpsendfile.setFilename(filename);
                                udpsendfile.setReceiverid(userid);
                                udpsendfile.setRecipient(username);
                                udpsendfile.setPosttime(date);
                                udpsendfile.setUserid(login.getId());
                                //将发送的数据存放到发文统计表
                                //将发送的数据存放到发文统计表
                                try {
                                    int count = udpsendfileService.insertUdpsendfile(udpsendfile);
                                    if (count != 1) {
                                        reStr = "没有记录到发文统计";
                                        jsonObject.put("str", reStr);
                                        return "successCallBack(" + jsonObject.toJSONString() + ")";
                                    }
                                    //将发送的数据添加到工具类,从session获取发送人的id
                                    String filepath="/usr/uploadImage/"+filename;
                                    //根据接收人的id查询接收人的姓名
                                    //String filepath = "D:\\imageTest\\" + filename;
                                    Filetoacept filetoacept = new Filetoacept();
                                    filetoacept.setDispatcher_id(login.getId());
                                    filetoacept.setFilename(filename);
                                    filetoacept.setReceiver_id(last.getUserid());
                                    filetoacept.setFilesaving(filepath);
                                    filetoacept.setReadunread((byte) 2);
                                    List<Filetoacept> list = new ArrayList<Filetoacept>();
                                    list.add(filetoacept);
                                    String json = jsonObject.toJSONString(list);
                                    String jsonRe = HttpSend.HttpPostWithJson(Ipall, json);
                                    if (!jsonRe.equals("发送成功")) {
                                        reStr = "发送失败";
                                        jsonObject.put("str", reStr);
                                        return "successCallBack(" + jsonObject.toJSONString() + ")";
                                    }
                                }catch (Exception e){
                                    reStr = "记录发文统计失败";
                                    jsonObject.put("str", reStr);
                                    return "successCallBack(" + jsonObject.toJSONString() + ")";
                                }
                            }
                        }
                    }
                }
                reStr = "发送成功";
                jsonObject.put("str", reStr);
                return "successCallBack(" + jsonObject.toJSONString() + ")";
            }catch (NullPointerException n){
                reStr = "请选择接收人";
                jsonObject.put("str", reStr);
                return "successCallBack(" + jsonObject.toJSONString() + ")";
            }
        }else{
            reStr="请选中要发送的文件";
            jsonObject.put("str", reStr);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
        }
    }
}
