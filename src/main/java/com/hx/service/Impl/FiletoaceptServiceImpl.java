package com.hx.service.Impl;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:58
 *@功能:
 */

import com.hx.dao.FiletoaceptMapper;
import com.hx.model.Filetoacept;
import com.hx.service.FiletoaceptService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class FiletoaceptServiceImpl implements FiletoaceptService {
    @Autowired
    private FiletoaceptMapper filetoaceptMapper;

    //获取收文列表
    public List<Filetoacept> listFileToAceptMsg(Integer id) {
        List<Filetoacept> filetoacepts = filetoaceptMapper.listFileToAceptMsg(id);
        if (!filetoacepts.isEmpty()){
            return filetoacepts;
        } else {
            return null;
        }
    }

    /**
     * 获取用户已读或者未读列表
     * @param receiverid
     * @param readunread
     * @return
     */
    public List<Filetoacept> getUserUnReadFileList(int receiverid, int readunread) {
        List<Filetoacept> filetoacepts = filetoaceptMapper.getUserUnReadFileList(receiverid,readunread);
        if (filetoacepts.isEmpty()){
            return filetoacepts;
        } else {
            return null;
        }
    }
    public void insertFtc(Filetoacept ftc) {
        filetoaceptMapper.insertFtc(ftc);
    }

    public List<Filetoacept> listFileToAceptMsgStauts(Integer receiverid) {
        List<Filetoacept> filetoacepts = filetoaceptMapper.listFileToAceptMsgStauts(receiverid);
        if (!filetoacepts.isEmpty()){
            return filetoacepts;
        } else {
            return null;
        }
    }

    public void updateReadunread(Integer id) {
        filetoaceptMapper.updateReadunread(id);
    }

    public Integer selectReCount(Integer id) {
        return filetoaceptMapper.selectReCount(id);
    }

    @Override
    public String selectUrl(Integer id) {
        return filetoaceptMapper.selectUrl(id);
    }
}
