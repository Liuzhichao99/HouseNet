package com.lzc.service;

import com.lzc.base.BaseService;
import com.lzc.entity.Dict;

import java.util.List;
import java.util.Map;

public interface DictService extends BaseService<Dict> {
    /**
     * 根据父节点的id查询子节点信息
     * @param parentId
     * @return
     */
    List<Map<String, Object>> findZnodes(Long parentId);

    /**
     * 根据父节点的dictCode获取子节点信息
     * @param dictCode
     * @return
     */
    List<Dict> findDictListByParentDictCode(String dictCode);

    /**
     * 根据父节点的id查询子节点信息
     * @param parentId
     * @return
     */
    List<Dict> findDictListByParentId(Long parentId);
}
