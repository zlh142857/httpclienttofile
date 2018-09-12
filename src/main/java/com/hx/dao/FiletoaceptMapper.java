package com.hx.dao;/*
 */

import com.hx.model.Filetoacept;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;

public interface FiletoaceptMapper {
    //获取收文列表
    List<Filetoacept> listFileToAceptMsg(int receiverid);

    //获取用户已读或者未读信息
    List<Filetoacept> getUserUnReadFileList(@Param(value = "receiverid") int receiverid,@Param(value = "readunread") int readunread);
}
