package com.hx.model;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:31
 *@功能:
 */

import java.io.Serializable;

public class Login implements Serializable {
    private static final long serialVersionUID = -3121217233854143647L;
    private Integer id;
    private String name;
    private String password;

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

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
