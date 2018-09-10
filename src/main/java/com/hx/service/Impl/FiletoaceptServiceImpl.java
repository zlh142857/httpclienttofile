package com.hx.service.Impl;/*
 *//*
 *@作者:张立恒
 *@时间:2018/9/10 9:58
 *@功能:
 */

import com.hx.dao.FiletoaceptMapper;
import com.hx.model.Filetoacept;
import com.hx.service.FiletoaceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiletoaceptServiceImpl implements FiletoaceptService {
    @Autowired
    private FiletoaceptMapper filetoaceptMapper;
}
