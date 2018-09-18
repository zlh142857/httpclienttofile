package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/11 16:12
 *@功能:在线预览文件
 */

import com.hx.service.FiletoaceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ShowFileController {
    @Autowired
    private FiletoaceptService filetoaceptService;
    /**
     *
     * 功能描述: 点击文件名称,在线预览
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/11 16:13
     */
    @RequestMapping(value="/showImage")
    public void showImageByType(String filename,Integer id,HttpServletResponse response) throws Exception{
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            /*/usr/uploadImage/*/
            inputStream = new FileInputStream(new File("/usr/uploadImage/"+filename));
            writer = response.getOutputStream();

            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                writer.write(buf, 0, len); //写
            }
            inputStream.close();
            filetoaceptService.updateReadunread(id);
        } catch (Exception e) {
            System.out.println("看这里+++++++++++++++++++出错了+++++++++++++++++++++++++++");
        } finally{
            try {
                if(inputStream != null){
                    inputStream.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("看这里finally+++出错了++++inputStream和OutputStream都是空的");
            }
        }
    }
}
