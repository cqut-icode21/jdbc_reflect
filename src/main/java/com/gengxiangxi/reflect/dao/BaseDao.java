package com.gengxiangxi.reflect.dao;

import java.util.List;

/**
 * @author 潘琴
 * 增删改查方法接口
 */
public interface BaseDao {
    /**
     * 查询所有
     *
     * @param clazz 类
     * @return 类的所有结果数组
     */
    <T> List<T> findAll(Class<T> clazz);

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id    要查询的对象的id
     * @return 查询的对象的所有信息
     */
    Object findById(Class<?> clazz, Object id);

    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    boolean delete(Class<?> clazz, Object id);

    /**
     * 新增
     *
     * @param clazz 新增的对象
     * @return 是否成功
     */
    boolean add(Class<?> clazz);

    /**
     * 修改
     *
     * @param clazz 对象
     * @param id     需要修改的对象的id
     * @return 是否成功
     */
    boolean update(Class<?> clazz, Object id);

}