package com.hx.dao;

import org.springframework.stereotype.Repository;

/**
 * @author 范聪敏
 * @date 2018/9/19 3:27
 * @desc
 */
@Repository
public interface DelMapper {  
    Integer deleteMany(int[] id_arr);
}
