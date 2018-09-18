package com.hx.config;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/18 15:48
 *@功能:
 */

import sun.misc.BASE64Decoder;

import java.io.*;

public class Base {
    public static InputStream base64(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
            BASE64Decoder decoder = new BASE64Decoder();

            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            byte[] bytes = decoder.decodeBuffer(str);//base64编码内容转换为字节数组
            InputStream inputStream = new ByteArrayInputStream(bytes);
            return inputStream;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
