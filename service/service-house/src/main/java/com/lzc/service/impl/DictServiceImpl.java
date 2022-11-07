package com.lzc.service.impl;

import com.lzc.base.BaseMapper;
import com.lzc.base.BaseServiceImpl;
import com.lzc.entity.Dict;
import com.lzc.mapper.DictMapper;
import com.lzc.service.DictService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;


    @Override
    public BaseMapper<Dict> getBaseMapper() {
        return dictMapper;
    }

    @Override
    public List<Map<String, Object>> findZnodes(Long parentId) {
        //根据父节点的id查询子节点信息
        List<Dict> dictList = dictMapper.findDictListByParentId(parentId);
        //将数据字典的信息转换为{id:1,name:全部分类,isParent:true}
        List<Map<String, Object>> znodes = new ArrayList<>();

        for (Dict dict : dictList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", dict.getId());
            map.put("name", dict.getName());
            map.put("isParent", dictMapper.getCountByParentId(dict.getId())>0);
            znodes.add(map);
        }

        return znodes;
    }

    @Override
    public List<Dict> findDictListByParentDictCode(String dictCode) {
        return dictMapper.findDictListByParentDictCode(dictCode);
    }

    @Override
    public List<Dict> findDictListByParentId(Long parentId) {
        return dictMapper.findDictListByParentId(parentId);
    }
}
