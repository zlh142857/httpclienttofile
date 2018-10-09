package com.hx.service.Impl;

import com.hx.dao.UdpsdelMapper;
import com.hx.service.UdpsdelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范聪敏
 * @date 2018/9/19 16:28
 * @desc
 */
@Service
public class UdpsdelServiceimpl implements UdpsdelService {
    @Autowired
    UdpsdelMapper ud;
    @Override
    public Integer udpsdelMany(int[] id_arr) {
        return ud.udpsdelMany(id_arr);
    }
}
