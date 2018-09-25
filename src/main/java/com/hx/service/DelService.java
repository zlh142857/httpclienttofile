package com.hx.service;

import org.springframework.stereotype.Service;

/**
 * @author 范聪敏
 * @date 2018/9/19 8:44
 * @desc
 */
@Service
public interface DelService {
    public Integer deleteMany(int[] id_arr);
}
