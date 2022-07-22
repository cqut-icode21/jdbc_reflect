package example.第三次作业.dao;

import example.第三次作业.Entity.Condition;

import java.util.List;

/**
 * @author LIXIN
 * @description 增删改查基类 T 代表实体类，S代表指定Sql生成类
 * @date 2022/4/7 20:04
 */
public abstract class BaseCRUD<T, S extends BaseSQL<T>> {

    public abstract S getSql();

    /**
     * @param entity 需要更新的数据
     * @return 更新完成后的数据
     */
    public abstract boolean updateOne(T entity, Condition condition);

    public abstract boolean saveOne(T entity);

    /**
     * @param conditions 删除条件
     * @return 受影响的条数
     */
    public abstract int deleteOne(Class<T> entity, Condition conditions);

    /**
     * @param conditions 查询条件
     * @return 查询的结果
     */
    public abstract List<T> query(Class<T> entity, Condition conditions);

}
