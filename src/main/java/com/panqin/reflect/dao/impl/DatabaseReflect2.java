package com.panqin.reflect.dao.impl;


import com.panqin.reflect.dao.BaseDao;
import com.panqin.reflect.utils.DatabaseUtil;
import com.panqin.reflect.utils.DatabaseUtil2;

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
public class DatabaseReflect2 implements BaseDao {
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
        // 1、获取数据库连接
        connection = DatabaseUtil2.getConnection();
        List list = new ArrayList();
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();


        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findAllSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            //statement.setObject(1,null);

            // 5、执行SQL语句
            System.out.println("findById:" + statement);
            resultSet = statement.executeQuery();
            // 6、获取执行结果
            while (resultSet.next()) {
                object = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(object, resultSet.getObject(i + 1));
                }
                list.add(object);
            }
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
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
        // 1、获取数据库连接
        connection = DatabaseUtil2.getConnection();
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();


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
        // 1、获取数据库连接
        connection = DatabaseUtil2.getConnection();
        Object object = null;
        Field[] fields = clazz.getDeclaredFields();
        int i=0;

        try {

            // 2、获取到SQL语句
            String sql = sqlDao.deleteSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            statement.setObject(1, id);

            // 5、执行SQL语句
            System.out.println("deleteById:" + statement);
            i = statement.executeUpdate();
            // 6、获取执行结果


        } catch (RuntimeException | SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (i>0){
            return true;
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
        // 1、获取数据库连接
        connection = DatabaseUtil2.getConnection();
        Class<?> c1=object.getClass();//因为参数没有本类，得创建变量
        Field[] fields = c1.getDeclaredFields();
        int  n=0;


        try {
            // 2、获取到SQL语句
            String sql = sqlDao.addSql(c1);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value =fields[i].get(object);
                statement.setObject(i+1,value);
            }
            n=statement.executeUpdate();
            System.out.println("add"+statement);


        } catch (RuntimeException | SQLException | IllegalAccessException  throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (n>0)
            return true;
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
        // 1、获取数据库连接
        connection = DatabaseUtil2.getConnection();
        Class<?> c1=object.getClass();//因为参数没有本类，得创建变量
        Field[] fields = c1.getDeclaredFields();
        int m=0;
        int i=0;

        try {
            // 2、获取到SQL语句
            String sql = sqlDao.updateSql(c1);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            for (; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object value =fields[i].get(object);
                statement.setObject(i+1,value);
            }
            statement.setObject(i+1, id);
            m=statement.executeUpdate();
            System.out.println("updateById+"+statement);


        } catch (RuntimeException | SQLException | IllegalAccessException  throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        if (m>0)
            return true;
        return false;
    }
}
