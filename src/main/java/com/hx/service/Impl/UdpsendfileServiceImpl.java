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

    public int insertUdpsendfile(Udpsendfile udpsendfile) {
        return udpsendfileMapper.insertUdpsendfile(udpsendfile);
    }

    public List<Udpsendfile> selectUpdsendfile(Integer id) {
        return udpsendfileMapper.selectUpdsendfile(id);
    }

    public Integer selectSendCount(Integer id) {
        return udpsendfileMapper.selectSendCount(id);
    }
}
