package com.hx.service.Impl;

import com.hx.dao.UdpsdelMapper;
import com.hx.service.UdpsdelService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 范聪敏
 * @date 2018/9/19 16:28
 * @desc
 */
public class UdpsdelServiceimpl implements UdpsdelService {
    @Autowired
    UdpsdelMapper ud;
    @Override
    public Integer udpsdelMany(int[] id_arr) {
        return ud.udpsdelMany(id_arr);
    }
}
