package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 15:53
 *@功能:
 */

import com.alibaba.fastjson.JSONObject;
import com.hx.config.FileUtil;
import com.hx.config.HttpSend;
import com.hx.model.*;
import com.hx.service.FiletoaceptService;
import com.hx.service.LoginService;
import com.hx.service.SendFileService;
import com.hx.service.UdpsendfileService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
     * @param:@RequestParam("file") final MultipartFile file, String userids
     * @return: 
     * @auther: 张立恒
     * @date: 2018/9/11 9:17
     */
    @RequestMapping("/sendFile")
    @ResponseBody
    public String sendFile(@RequestParam("file") MultipartFile file, String userids,HttpServletRequest request) throws ParseException {
        //ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        JSONObject jsonObject = new JSONObject();
        String reStr = "";
        if (file.isEmpty()) {
            jsonObject.put("str", reStr);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
        }
        if (userids.equals(null) || userids.equals("")) {
            jsonObject.put("str", reStr);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
        } else {
            String[] str = userids.split(",");
            int size = str.length;
            for (int i = 0; i < size; i++) {
                //根据userID查询每个用户对应的最后登录的两个IP
                Last last = sendFileService.selectIpsByUserid(Integer.valueOf(str[i]));
                if (last == null) {
                    reStr = "请选择有效的接收人";
                    jsonObject.put("str", reStr);
                    return "successCallBack(" + jsonObject.toJSONString() + ")";
                } else {
                    //将获取到的file和两个IP发送到工具类
                    String sendRe = HttpSend.sendHttp(file, last.getTerritory_ip(), request);
                    if (sendRe.equals("失败")) {
                        reStr = "发送文件失败";
                        jsonObject.put("str", reStr);
                        return "successCallBack(" + jsonObject.toJSONString() + ")";
                    } else {
                        //将获取到的文件数据发送到工具类
                        String filename = file.getOriginalFilename();
                        String username = last.getUsername();
                        String Ipall = last.getTerritory_ip();
                        Integer userid = last.getUserid();
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = sdf.format(date);
                        Date now = sdf.parse(time);
                        Udpsendfile udpsendfile = new Udpsendfile();
                        udpsendfile.setFilename(filename);
                        udpsendfile.setReceiverid(userid);
                        udpsendfile.setRecipient(username);
                        udpsendfile.setPosttime(now);
                        //将发送的数据存放到发文统计表
                        try {
                            int count = udpsendfileService.insertUdpsendfile(udpsendfile);
                            if (count != 1) {
                                reStr = "没有记录到发文统计";
                                jsonObject.put("str", reStr);
                                return "successCallBack(" + jsonObject.toJSONString() + ")";
                            }
                        } catch (Exception e) {
                            reStr = "记录发文统计失败";
                            jsonObject.put("str", reStr);
                            return "successCallBack(" + jsonObject.toJSONString() + ")";
                        }
                        //将发送的数据添加到工具类,从session获取发送人的id
                        /*HttpSession session = request.getSession();
                        Login login=(Login)session.getAttribute("login");*/
                        //String filepath="/usr/uploadImage/"+filename;
                        //根据接收人的id查询接收人的姓名
                        String filepath = "D:\\imageTest\\" + filename;
                        Filetoacept filetoacept = new Filetoacept();
                        //filetoacept.setDispatcher_id(login.getId());
                        filetoacept.setFilename(filename);
                        filetoacept.setReceiver_id(last.getUserid());
                        filetoacept.setFilesaving(filepath);
                        filetoacept.setReadunread((byte) 1);
                        List<Filetoacept> list = new ArrayList<Filetoacept>();
                        list.add(filetoacept);
                        String json = jsonObject.toJSONString(list);
                        String jsonRe = HttpSend.HttpPostWithJson(Ipall, json);
                        if (jsonRe.equals("发送失败")) {
                            reStr = "发送失败";
                            jsonObject.put("str", reStr);
                            return "successCallBack(" + jsonObject.toJSONString() + ")";
                        }
                    }
                }
            }
            reStr = "发送成功";
            jsonObject.put("str", reStr);
            return "successCallBack(" + jsonObject.toJSONString() + ")";
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
}
