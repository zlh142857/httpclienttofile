package com.hx.service.Impl;

import com.hx.dao.ReceiveMapper;
import com.hx.model.Filetoacept;
import com.hx.service.ReceiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/20 16:13
 * @desc
 */
@Service
public class ReceiveServiceimpl implements ReceiveService {

    @Autowired
    private ReceiveMapper receiveMapper;
    public List<Filetoacept> findAll() {
        List<Filetoacept> filetoacept = receiveMapper.findAll();
        return filetoacept;
    }
}
