package com.panqin.reflect.dao.impl;

import com.panqin.reflect.dao.BaseDao;
import com.panqin.reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

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
        Field[] fields = clazz.getDeclaredFields();
        List<T> list = null;
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.findAllSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            list = new ArrayList<>();
            int n = 0;
            // 遍历结果集
            while (resultSet.next()) {
                T object = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                list.add(object);
            }

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
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
            System.out.println("findById:" + statement);
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
        Field[] fields = clazz.getDeclaredFields();

        //获得数据库连接
        connection = DatabaseUtil.getConnection();
        int i = 0;

        try {
            //获得SQL语句
            String sql = sqlDao.deleteSql(clazz);

            //预编译
            statement = connection.prepareStatement(sql);
            statement.setObject(1,id);

            System.out.println("deleteById:" + statement);
            i = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            //释放资源
            DatabaseUtil.close(connection,statement,resultSet);
        }
        if (i > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 新增
     *
     * @param object 新增的对象
     * @return 是否成功
     */
    @Override
    public boolean add(Object object) {
        connection = DatabaseUtil.getConnection();
        Class<?> c1 = object.getClass();
        Field[] fields = c1.getDeclaredFields();
        String sql = sqlDao.addSql(c1);
        int n = 0;
        try {
            statement = connection.prepareStatement(sql);

            for (int i =0;i < fields.length;i++){
                fields[i].setAccessible(true);
                Object value = fields[i].get(object);
                statement.setObject(i + 1,value);
            }

            n = statement.executeUpdate();
            System.out.println("add " + statement);
        }catch (SQLException | IllegalAccessException e ){
            e.printStackTrace();
        }finally {
            //释放资源
            DatabaseUtil.close(connection,statement,resultSet);
        }
        if (n > 0){
            return true;
        }else {
            return false;
        }
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
        connection = DatabaseUtil.getConnection();
        Class<?> c2 = object.getClass();
        Field[] fields = c2.getDeclaredFields();
        String sql = sqlDao.updateSql(c2);
        int n = 0;
        try {
            statement = connection.prepareStatement(sql);
            int i = 0;
            for (; i < fields.length; i++){
                fields[i].setAccessible(true);
                Object value = fields[i].get(object);
                statement.setObject(i+1,value);
            }

            statement.setObject(i+1,id);
            n = statement.executeUpdate();
            System.out.println("updateById " + statement);

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }

        if (n > 0){
            return true;
        }else {
            return false;
        }
    }
}
