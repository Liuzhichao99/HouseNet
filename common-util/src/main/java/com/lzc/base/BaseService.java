package com.lzc.base;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/10/31
 * Author:ybc
 * Description:
 */
public interface BaseService<T> {

    /**
     * 查询所有数据
     * @return
     */
    List<T> findAll();

    /**
     * 查询分页数据
     * @param filters
     * @return
     */
    PageInfo<T> findPage(Map<String, Object> filters);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 添加数据
     * @param t
     */
    void save(T t);

    /**
     * 修改数据
     * @param t
     */
    void update(T t);

    /**
     * 删除数据
     * @param id
     */
    void delete(Long id);

}
