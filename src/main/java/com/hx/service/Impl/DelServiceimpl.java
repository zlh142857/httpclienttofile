package com.hx.service.Impl;

import com.hx.dao.DelMapper;
import com.hx.service.DelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范聪敏
 * @date 2018/9/19 8:38
 * @desc
 */
@Service
public class DelServiceimpl implements DelService {
    @Autowired
    DelMapper delMapper;

    @Override
    public Integer deleteMany(int[] id_arr) {

        return delMapper.deleteMany(id_arr);
    }




}
