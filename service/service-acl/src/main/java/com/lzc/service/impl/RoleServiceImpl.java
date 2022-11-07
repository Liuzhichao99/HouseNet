package com.lzc.service.impl;

import com.lzc.base.BaseMapper;
import com.lzc.base.BaseServiceImpl;
import com.lzc.entity.Role;
import com.lzc.mapper.RoleMapper;
import com.lzc.service.RoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DubboService
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getBaseMapper() {
        return roleMapper;
    }
}
