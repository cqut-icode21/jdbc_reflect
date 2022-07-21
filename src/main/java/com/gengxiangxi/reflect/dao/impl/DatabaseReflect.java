package com.gengxiangxi.reflect.dao.impl;

import com.gengxiangxi.reflect.annotation.Id;
import com.gengxiangxi.reflect.dao.BaseDao;
import com.gengxiangxi.reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.findAllSql(clazz);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
//            ---------------------------------------------------------------
            List list = new ArrayList();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                list.add(rowData);
            }
            return list;
//            ---------------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
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
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.findByIdSql(clazz);
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            resultSet = statement.executeQuery();
//            ---------------------------------------------------------------
            List list = new ArrayList();
            ResultSetMetaData md = resultSet.getMetaData();
            int columnCount = md.getColumnCount();
            while (resultSet.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultSet.getObject(i));
                }
                list.add(rowData);
            }
            return list;
//            ---------------------------------------------------------------
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return null;
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
        //连接数据库
        connection = DatabaseUtil.getConnection();
        //获取指令          delete from teacher where
        StringBuilder sql = new StringBuilder(sqlDao.deleteSql(clazz));
        try {
            //获取所有字段
            Field[] fields = clazz.getDeclaredFields();
            //遍历所有字段，并判断是否拥有Id注解
            for (Field field : fields) {
                //获取当前字段的Id注解
                Id idAnnotation = field.getAnnotation(Id.class);
                //判断Id注解是否为空
                if (idAnnotation.idName() != null) {
                    //不为空则将Id注解名加入字符串中  delete from teacher where tid = 10
                    sql.append(idAnnotation.idName()).append(" = ").append(id);
                    break;
                }
            }
            //命令预加载
            statement = connection.prepareStatement(sql.toString());
            //执行命令，并返回是否删除成功的boolean值
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            //输出错误信息
            e.printStackTrace();
        } finally {
            //释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 新增
     *
     * @param clazz 新增的类对象
     * @return 是否成功
     */
    @Override
    public boolean add(Class<?> clazz) {
        //连接数据库
        connection = DatabaseUtil.getConnection();
        //创建clazz的实例化对象
        Object o = TeacherImpl.getInstance(clazz);
        //获取实例化对象的类的对象
        Class<?> oClazz = o.getClass();
        //获取类对象的所有字段
        Field[] oFields = oClazz.getDeclaredFields();
        try {
            //获取指令      insert into teacher values(?,?,?)
            String sql = sqlDao.addSql(clazz);
            //预加载指令
            statement = connection.prepareStatement(sql);
            //记录占位符的位置
            int count = 0;
            //便利所有字段
            for (Field oField : oFields) {
                //设置字段可操作
                oField.setAccessible(true);
                //设置当前占位符
                statement.setObject(++count, oField.get(o));
            }               //insert into teacher values(1, gxx, 20)
            //执行命令，并返回是否添加成功的boolean值
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            //输出错误信息
            e.printStackTrace();
        } finally {
            //释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

    /**
     * 修改
     *
     * @param clazz 类
     * @param id    需要删除的对象的id
     * @return 是否成功
     */
    @Override
    public boolean update(Class<?> clazz,Object id) {
        System.out.println(findById(clazz, id));
        connection = DatabaseUtil.getConnection();
        String sql = sqlDao.updateSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return false;
    }

}
