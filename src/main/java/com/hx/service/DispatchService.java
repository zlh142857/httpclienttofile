package com.hx.service;

import com.hx.model.Udpsendfile;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/21 16:20
 * @desc
 */
@Service
public interface DispatchService {
    public List<Udpsendfile> dispatch();
}
