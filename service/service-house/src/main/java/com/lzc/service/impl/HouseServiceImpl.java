package com.lzc.service.impl;

import com.lzc.base.BaseMapper;
import com.lzc.base.BaseServiceImpl;
import com.lzc.entity.House;
import com.lzc.mapper.DictMapper;
import com.lzc.mapper.HouseMapper;
import com.lzc.service.HouseService;
import com.sun.imageio.plugins.png.PNGImageReader;
import org.springframework.beans.factory.annotation.Autowired;

public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private DictMapper dictMapper;


    @Override
    public BaseMapper<House> getBaseMapper() {
        return houseMapper;
    }
}
