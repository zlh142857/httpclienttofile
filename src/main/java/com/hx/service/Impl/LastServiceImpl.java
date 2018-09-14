package com.hx.service.Impl;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:56
 *@功能:
 */

import com.hx.dao.LastMapper;
import com.hx.model.Last;
import com.hx.service.LastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LastServiceImpl implements LastService {
    @Autowired
    private LastMapper lastMapper;

    public int insertLoginLog(Last last){
        return lastMapper.insertLoginLog(last);
    }

}
