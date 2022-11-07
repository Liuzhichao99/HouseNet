package com.lzc.mapper;

import com.lzc.base.BaseMapper;
import com.lzc.entity.Dict;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 根据父节点的id查询子节点信息
     * @param parentId
     * @return
     */
    List<Dict> findDictListByParentId(Long parentId);

    /**
     * 根据父节点的id查询子节点的数量
     * @param id
     * @return
     */
    Integer getCountByParentId(Long id);

    /**
     * 根据父节点的dictCode查询子节点的信息
     * @param dictCode
     * @return
     */
    List<Dict> findDictListByParentDictCode(String dictCode);

    /**
     * 根据id查询name
     * @param id
     * @return
     */
    String getDictNameById(Long id);
}
