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
import java.util.Scanner;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 实现SQL语句 及 增删改查方法
 */
public class DatabaseReflect implements BaseDao {
    Student student = new Student();
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
        List<T> list = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.findAllSql(clazz);

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];

                    field.setAccessible(true);

                    field.set(t, resultSet.getObject(i + 1));
                }
                list.add(t);
            }

            return list;
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {

            DatabaseUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id    要查询的对象的id
     * @return 查询的对象的所有信息
     */
    @Override
    public Object findById(Class<?> clazz, Object id) {
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();

        connection = DatabaseUtil.getConnection();
        try {

            String sql = sqlDao.findByIdSql(clazz);

            statement = connection.prepareStatement(sql);

            statement.setObject(1, id);

            resultSet = statement.executeQuery();

            object = clazz.newInstance();

            while (resultSet.next()) {
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    field.setAccessible(true);
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

        connection = DatabaseUtil.getConnection();
        try {

            String sql = sqlDao.deleteSql(clazz);

            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);

            int dropRow = statement.executeUpdate();
            if (dropRow != 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 7、释放资源
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
        connection = DatabaseUtil.getConnection();
        Field[] obj = object.getClass().getDeclaredFields();
        try {
            String sql = sqlDao.addSql(object.getClass());
            statement = connection.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                obj[i].setAccessible(true);
                statement.setObject(i + 1, obj[i].get(object));
            }
            int addRow = statement.executeUpdate();
            if (addRow != 0) {
                System.out.println("添加成功");
                return true;
            } else {
                System.out.println("添加失败");
                return false;
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }finally {

            DatabaseUtil.close(connection, statement, resultSet);
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

        Field[] obj = object.getClass().getDeclaredFields();

        connection = DatabaseUtil.getConnection();
        try {

            String sql = sqlDao.updateSql(object.getClass());

            statement = connection.prepareStatement(sql);

            for (int i = 0; i < obj.length; i++) {
                obj[i].setAccessible(true);
                statement.setObject(i + 1, obj[i].get(object));
            }
            statement.setObject(4, id);
            int updateRow = statement.executeUpdate();
            if (updateRow != 0) {
                System.out.println("修改成功");
                return true;
            } else {
                System.out.println("修改失败");

            }

        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {

            DatabaseUtil.close(connection, statement, resultSet);
        }

        return true;
    }
}