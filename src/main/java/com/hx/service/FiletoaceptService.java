package com.hx.service;/*
 */

import com.hx.model.Filetoacept;
import org.springframework.ui.Model;

import java.util.List;

import com.hx.model.Filetoacept;

public interface FiletoaceptService {

    //获取收文列表
    List<Filetoacept>  listFileToAceptMsg(int receiverid);

    //获取已读或者未读收文列表
    List<Filetoacept>  getUserUnReadFileList(int receiverid,int readunread);
    void insertFtc(Filetoacept ftc);//将数据存放今进接收文件列表
}
