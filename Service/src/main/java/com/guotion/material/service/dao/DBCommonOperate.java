package com.guotion.material.service.dao;

/**
 * Created by Administrator on 2015/1/6 0006.
 */


import com.guotion.material.service.bean.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 数据库公用操作
 */
public interface DBCommonOperate {

    /**
     * 添加一条记录
     * @param entityClass
     * @param entity
     * @param <T>
     */
    public <T> void add(Class<T> entityClass, Object entity);

    /**
     * 删除一个记录
     * @param entity
     */
    public void delete(Object entity);

    /**
     * 更新数据
     * @param entityClass
     * @param entity
     * @param <T>
     */
    public <T> void update(Class<T> entityClass, Object entity);
    /**
     * 查询单个记录
     * @param entityClass
     * @param entityId
     * @param <T>
     * @return
     */
    public <T> T find(Class<T> entityClass, Object entityId);

    /**
     * 查询方法(不排序)
     * @param entityClass
     * @param whereHQL
     * @param params
     * @param <T>
     * @return
     */
    public <T> List<T> find(Class<T> entityClass, String whereHQL, Object[] params);

    /**
     * 查询方法,根据提供的key排序
     * @param entityClass
     * @param whereHQL
     * @param params
     * @param order
     * @param <T>
     * @return
     */
    public <T> List<T> find(Class<T> entityClass, String whereHQL, Object[] params, LinkedHashMap<String, String> order);

    /**
     * 查询分页数据
     * @param entityClass
     * @param startIndex 开始索引
     * @param resultCount 符合条件的最大结果数
     * @param whereHQL where语句
     * @param order 排序
     * @param <T>
     * @return
     */
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount, String whereHQL, Object[] params, LinkedHashMap<String, String> order);

    /**
     * 查询分页数据
     * @param entityClass
     * @param startIndex
     * @param resultCount
     * @param <T>
     * @return
     */
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount);

    /**
     * 查询分页数据 ，有条件
     * @param entityClass
     * @param startIndex
     * @param resultCount
     * @param whereHQL
     * @param params
     * @param <T>
     * @return
     */
    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount, String whereHQL, Object[] params);

    /**
     * 查询分页数据，要排序
     * @param entityClass
     * @param startIndex
     * @param resultCount
     * @param order
     * @param <T>
     * @return
     */

    public <T> QueryResult<T> getScrollData(Class<T> entityClass, int startIndex, int resultCount, LinkedHashMap<String, String> order);

//    /**
//     * 多表查询分页
//     * @param startIndex
//     * @param resultCount
//     * @param hql
//     * @param params
//     * @param <T>
//     * @return
//     */
//    public <T> QueryResult<T> getScrollData(final int startIndex, final int resultCount, final String hql, final Object[]params);

    public <T> QueryResult<T> getScrollData(final Class<T> entityClass, final int startIndex, final int resultCount, final String whereHQL, final Object[]params,final List paralist, final LinkedHashMap<String, String> order);
}
