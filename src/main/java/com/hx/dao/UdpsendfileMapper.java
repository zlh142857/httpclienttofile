package com.hx.dao;/*
 */

import com.hx.model.Udpsendfile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UdpsendfileMapper {
    int insertUdpsendfile(Udpsendfile udpsendfile);//将发送的数据存放到发文统计表

    List<Udpsendfile> selectUpdsendfile(@Param("pageStart") Integer pageStart,
                                        @Param("pageSize") Integer pageSize,@Param("id") Integer id);//分页查询发文统计列表
}
