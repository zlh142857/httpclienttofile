package com.hx.service.Impl;

import com.hx.dao.DispatchMapper;
import com.hx.model.Filetoacept;
import com.hx.model.Udpsendfile;
import com.hx.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/21 16:20
 * @desc
 */
@Service
public class DispatchServiceimpl implements DispatchService {
    @Autowired
    private DispatchMapper dispatchMapper;
    @Override
    public List<Udpsendfile> dispatch() {
        List<Udpsendfile> udpsendfile = dispatchMapper.dispatch();
        return udpsendfile;
    }
}
