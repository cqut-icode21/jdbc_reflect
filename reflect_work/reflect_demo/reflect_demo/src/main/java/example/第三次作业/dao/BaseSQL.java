package example.第三次作业.dao;

import example.第三次作业.Entity.Condition;

public interface BaseSQL<T> {
    /**
     * @param clazz 需要更新的数据
     * @return 更新完成后的数据
     */
    public abstract String updateOne(T entity, Condition condition) throws Exception;
    public abstract String saveOne(Class<T> clazz);

    /**
     * @param clazz 删除条件
     * @return 受影响的条数
     */
    public abstract String deleteOne(Class<T> clazz,String conditions);

    /**
     * @param clazz 查询条件
     * @return 查询的结果
     */
    public abstract String query(Class<T> clazz, String conditions);

    public abstract String createTable(Class<?> clazz) throws Exception;
}