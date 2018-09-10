package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:47
 *@功能:
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Udpsendfile {
    private Integer id;
    private Integer receiverid;
    private String filename;
    private String recipient;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date posttime;
}
