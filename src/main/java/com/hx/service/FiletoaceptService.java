package com.hx.service;/*
 */

import com.hx.model.Filetoacept;

import java.util.List;


public interface FiletoaceptService {

    //获取收文列表
    List<Filetoacept>  listFileToAceptMsg(Integer receiverid);

    //获取已读或者未读收文列表
    List<Filetoacept>  getUserUnReadFileList(int receiverid,int readunread);
    void insertFtc(Filetoacept ftc);//将数据存放今进接收文件列表

    List<Filetoacept> listFileToAceptMsgStauts(Integer receiverid);

    void updateReadunread(Integer id);//修改未读为已读

    Integer selectReCount(Integer id);
}
