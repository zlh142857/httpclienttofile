package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:31
 *@功能:
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable {
    private static final long serialVersionUID = -3121217233854143647L;
    private Integer id;
    private String name;  //登录人姓名
    private String password;
    private String ip;  //ip地址
    private String time;   //登录时间
    private Integer sendCount;  //发文次数
    private Integer reCount;  //收文次数
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getReCount() {
        return reCount;
    }

    public void setReCount(Integer reCount) {
        this.reCount = reCount;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                ", sendCount=" + sendCount +
                ", reCount=" + reCount +
                '}';
    }
}
