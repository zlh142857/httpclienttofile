package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:47
 *@功能:
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Udpsendfile implements Serializable {
    private static final long serialVersionUID = -4709132916177386719L;
    private Integer id;
    private Integer receiverid;
    private String filename;
    private String recipient;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date posttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(Integer receiverid) {
        this.receiverid = receiverid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    @Override
    public String toString() {
        return "Udpsendfile{" +
                "id=" + id +
                ", receiverid=" + receiverid +
                ", filename='" + filename + '\'' +
                ", recipient='" + recipient + '\'' +
                ", posttime=" + posttime +
                '}';
    }
}
