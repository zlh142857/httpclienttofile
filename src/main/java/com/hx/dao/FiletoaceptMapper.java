package com.hx.dao;/*
 */

import com.hx.model.Filetoacept;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import java.util.List;

import com.hx.model.Filetoacept;

public interface FiletoaceptMapper {
    //获取收文列表
    List<Filetoacept> listFileToAceptMsg(int receiverid);

    //获取用户已读或者未读信息
    List<Filetoacept> getUserUnReadFileList(@Param(value = "receiverid") int receiverid,@Param(value = "readunread") int readunread);
    void insertFtc(Filetoacept ftc);//将数据存放今进接收文件列表
}
