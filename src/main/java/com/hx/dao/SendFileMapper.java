package com.hx.dao;/*
 */

import com.hx.model.Last;
import org.apache.ibatis.annotations.Param;

public interface SendFileMapper {
    Last selectIpsByUserid(@Param("s") Integer s);//根据userID查询每个用户对应的最后登录的两个IP
}
