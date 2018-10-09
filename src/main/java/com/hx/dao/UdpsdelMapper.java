package com.hx.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 范聪敏
 * @date 2018/9/19 16:27
 * @desc
 */
@Repository
public interface UdpsdelMapper {
    Integer udpsdelMany(int[] id_arr);
}
