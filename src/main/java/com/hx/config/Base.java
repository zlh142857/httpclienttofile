package com.hx.config;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/18 15:48
 *@功能:
 */

import sun.misc.BASE64Decoder;

import java.io.*;

public class Base {
    public static InputStream base64(String filename) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(filename);//base64编码内容转换为字节数组
        for (int i = 0; i < b.length; ++i) {
            // 调整异常数据
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        InputStream inputStream = new ByteArrayInputStream(b);
        return inputStream;
    }

}
