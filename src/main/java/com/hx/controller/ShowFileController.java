package com.hx.controller;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/11 16:12
 *@功能:在线预览文件
 */

import com.hx.service.FiletoaceptService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public void showImageByType(Integer id,HttpServletResponse response) throws Exception{
        /*InputStream inputStream = null;
        OutputStream writer = null;
        String path=filetoaceptService.selectUrl(id);
        filetoaceptService.updateReadunread(id);
        try {
            //*usr/uploadImage/
            inputStream = new FileInputStream(new File(path));
            writer = response.getOutputStream();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buf)) != -1) {
                writer.write(buf, 0, len); //写
            }
            inputStream.close();
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
        }*/
        String path=filetoaceptService.selectUrl(id);
        filetoaceptService.updateReadunread(id);
        // photoUrl为接收到的路径
        if(StringUtils.isNotBlank(path)){
            File file = new File(path);
            // File file=new File(photoUrl);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file);
                     BufferedInputStream bis = new BufferedInputStream(fis, 1024);
                     ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);) {
                    byte[] cache = new byte[1024];
                    int length = 0;
                    while ((length = bis.read(cache)) != -1) {
                        bos.write(cache, 0, length);
                    }
                    String str=path.substring(path.lastIndexOf("."));
                    if(".pdf" .equals(str)){
                        response.setHeader("Content-type","application/pdf");
                        response.getOutputStream().write(bos.toByteArray());
                    }

                }
            }
        }

    }
}
