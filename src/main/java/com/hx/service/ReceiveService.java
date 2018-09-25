package com.hx.service;

import com.hx.model.Filetoacept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范聪敏
 * @date 2018/9/20 16:12
 * @desc
 */
@Service
public interface ReceiveService {
    List<Filetoacept> findAll();
}
