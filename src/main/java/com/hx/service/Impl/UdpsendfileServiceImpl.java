package com.hx.service.Impl;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 10:00
 *@功能:
 */

import com.hx.dao.UdpsendfileMapper;
import com.hx.model.Udpsendfile;
import com.hx.service.UdpsendfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UdpsendfileServiceImpl implements UdpsendfileService {
    @Autowired
    private UdpsendfileMapper udpsendfileMapper;

    public void insertUdpsendfile(Udpsendfile udpsendfile) {
        udpsendfileMapper.insertUdpsendfile(udpsendfile);
    }

    public List<Udpsendfile> selectUpdsendfile(Integer pageStart, Integer pageSize,Integer id) {
        return udpsendfileMapper.selectUpdsendfile(pageStart,pageSize,id);
    }
}
