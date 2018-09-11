package com.hx.service.Impl;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/11 9:38
 *@功能:
 */

import com.hx.dao.SendFileMapper;
import com.hx.model.Last;
import com.hx.service.SendFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendFileServiceImpl implements SendFileService {
    @Autowired
    private SendFileMapper sendFileMapper;

    public Last selectIpsByUserid(Integer s) {
        return sendFileMapper.selectIpsByUserid(s);
    }
}
