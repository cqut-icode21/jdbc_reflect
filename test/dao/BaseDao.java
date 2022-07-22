package test.dao;

import java.sql.SQLException;

/**
 * @author 龙
 */
public interface BaseDao {
    /**
     * 查询所有
     *
     * @param clazz 类
     */
    <T> void findAll(Class<T> clazz);

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id    要查询的对象的id
     */
    void findById(Class<?> clazz, Object id);

    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     */
    void delete(Class<?> clazz, Object id);

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */
    boolean add(Object object) throws SQLException;

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     */
    void update(Object object, Object id);

}