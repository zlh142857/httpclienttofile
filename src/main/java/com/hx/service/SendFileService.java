package com.hx.service;/*
 */

import com.hx.model.Last;

public interface SendFileService {
    Last selectIpsByUserid(Integer s);//根据userID查询每个用户对应的最后登录的两个IP
}
