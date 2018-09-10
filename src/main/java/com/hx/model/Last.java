package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:34
 *@功能:
 */

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Last implements Serializable {
    private Integer id;
    private Integer userid;
    private String client_ip;
    private String username;
    private String territory_ip;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date landing_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTerritory_ip() {
        return territory_ip;
    }

    public void setTerritory_ip(String territory_ip) {
        this.territory_ip = territory_ip;
    }

    public Date getLanding_time() {
        return landing_time;
    }

    public void setLanding_time(Date landing_time) {
        this.landing_time = landing_time;
    }

    @Override
    public String toString() {
        return "Last{" +
                "id=" + id +
                ", userid=" + userid +
                ", client_ip='" + client_ip + '\'' +
                ", username='" + username + '\'' +
                ", territory_ip='" + territory_ip + '\'' +
                ", landing_time=" + landing_time +
                '}';
    }
}
