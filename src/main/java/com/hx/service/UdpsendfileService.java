package com.hx.service;/*
 */

import com.hx.model.Udpsendfile;

import java.util.List;

public interface UdpsendfileService {
    void insertUdpsendfile(Udpsendfile udpsendfile);//将发送的数据存放到发文统计表

    List<Udpsendfile> selectUpdsendfile(Integer pageStart, Integer pageSize,Integer id);//分页查询发文统计列表
}
