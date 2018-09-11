package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 16:00
 *@功能:
 */


import java.io.Serializable;

public class FileData implements Serializable {
    private static final long serialVersionUID = 2144347562398115627L;
    private String filename;
    private String Ip;
    private String Ipall;
    private String sendTime;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getIpall() {
        return Ipall;
    }

    public void setIpall(String ipall) {
        Ipall = ipall;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "filename='" + filename + '\'' +
                ", Ip='" + Ip + '\'' +
                ", Ipall='" + Ipall + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
