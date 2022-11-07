package com.lzc.service.impl;

import com.lzc.base.BaseMapper;
import com.lzc.base.BaseServiceImpl;
import com.lzc.entity.Admin;
import com.lzc.mapper.AdminMapper;
import com.lzc.service.AdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public BaseMapper<Admin> getBaseMapper() {
        return adminMapper;
    }
}
