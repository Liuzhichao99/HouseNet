package com.lzc.base;

import com.lzc.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/10/31
 * Author:ybc
 * Description:
 */
public abstract class BaseServiceImpl<T> {

    public abstract BaseMapper<T> getBaseMapper();

    public List<T> findAll(){
        return getBaseMapper().findAll();
    }

    public PageInfo<T> findPage(Map<String, Object> filters){
        int pageNum = CastUtil.castInt(filters.get("pageNum"), 1);
        int pageSize = CastUtil.castInt(filters.get("pageSize"), 5);
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = getBaseMapper().findPage(filters);
        return new PageInfo<>(list, 10);
    }

    public T getById(Long id){
        return getBaseMapper().getById(id);
    }

    public void save(T t){
        getBaseMapper().save(t);
    }

    public void update(T t){
        getBaseMapper().update(t);
    }

    public void delete(Long id){
        getBaseMapper().delete(id);
    }

}
