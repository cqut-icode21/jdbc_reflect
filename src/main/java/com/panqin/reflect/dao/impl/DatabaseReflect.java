package com.panqin.reflect.dao.impl;

import com.panqin.reflect.dao.BaseDao;
import com.panqin.reflect.entities.Student;
import com.panqin.reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 实现SQL语句 及 增删改查方法
 */
public class DatabaseReflect implements BaseDao {
    /**
     * 数据库连接
     */
    Connection connection = null;
    /**
     * 预编译
     */
    PreparedStatement statement = null;
    /**
     * 结果集
     */
    ResultSet resultSet = null;
    /**
     * SQL语句实现类
     */
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    /**
     * 查询所有
     *
     * @param clazz 类
     * @return 类的所有结果数组
     */
    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        Field[] fields= clazz.getDeclaredFields();
        Object object;
        List<T> list=new ArrayList<>();

//        建立表连接
        connection=DatabaseUtil.getConnection();
        try {
//            获取sql语句
            String sql=sqlDao.findAllSql(clazz);
//            预编译语句
            statement= connection.prepareStatement(sql);
//            执行语句
            resultSet=statement.executeQuery();
//            遍历结果
            while (resultSet.next()) {
                object=clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                list.add((T) object);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id 要查询的对象的id
     * @return 查询的对象的所有信息
     */
    @Override
    public Object findById(Class<?> clazz, Object id) {
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findByIdSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            statement.setObject(1, id);

            // 5、执行SQL语句
            resultSet = statement.executeQuery();
            // 6、获取执行结果
            object = clazz.newInstance();
            // 遍历结果集
            while (resultSet.next()) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
            }
            return object;
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return object;
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    @Override
    public boolean delete(Class<?> clazz, Object id) {
        connection=DatabaseUtil.getConnection();
        try {
            String sql=sqlDao.deleteSql(clazz);
            System.out.println(sql);
            statement=connection.prepareStatement(sql);
            statement.setObject(1,id);

            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */
    @Override
    public boolean add(Object object) {
        Field[] fields=object.getClass().getDeclaredFields();

//        获取连接
        connection=DatabaseUtil.getConnection();
        try {
//            获取语句
            String sql=sqlDao.addSql(object.getClass());
//            预编译
            statement=connection.prepareStatement(sql);
//            填充
            for (int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object info=fields[i].get(object);
                statement.setObject((i+1),info);
            }
//            执行
            int i=statement.executeUpdate();
            return i>0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     * @return 是否成功
     */
    @Override
    public boolean update(Object object, Object id) {
        Object ori=findById(object.getClass(),id);
        Field field[]=object.getClass().getDeclaredFields();

        connection=DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.updateSql(object.getClass());
            statement = connection.prepareStatement(sql);
            for (int i=0;i<field.length;i++){
                field[i].setAccessible(true);
                Object o=field[i].get(object);
                if (o!=null&&!"-1".equals(String.valueOf(o))){
                    statement.setObject((i+1),o);
                }
                else statement.setObject((i+1),(field[i].get(ori)));
            }
            statement.setObject((field.length+1),id);

            return statement.executeUpdate()>0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }
}
