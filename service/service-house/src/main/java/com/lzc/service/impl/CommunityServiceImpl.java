package com.lzc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzc.base.BaseMapper;
import com.lzc.base.BaseServiceImpl;
import com.lzc.entity.Community;
import com.lzc.mapper.CommunityMapper;
import com.lzc.mapper.DictMapper;
import com.lzc.service.CommunityService;
import com.lzc.util.CastUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DubboService
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private DictMapper dictMapper;

    @Override
    public BaseMapper<Community> getBaseMapper() {
        return communityMapper;
    }

    public PageInfo<Community> findPage(Map<String, Object> filters) {
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 5);
        PageHelper.startPage(pageNum, pageSize);
        List<Community> list = getBaseMapper().findPage(filters);
        for (Community community : list) {
            community.setAreaName(dictMapper.getDictNameById(community.getAreaId()));
            community.setPlateName(dictMapper.getDictNameById(community.getPlateId()));
        }
        return new PageInfo<>(list, 10);
    }

}
