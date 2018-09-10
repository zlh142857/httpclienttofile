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

@Service
public class UdpsendfileServiceImpl implements UdpsendfileService {
    @Autowired
    private UdpsendfileMapper udpsendfileMapper;
}
