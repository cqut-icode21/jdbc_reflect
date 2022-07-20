package com.baoxiangjie.mysql.impl.daoImpl;

import com.baoxiangjie.mysql.annotation.Id;
import com.baoxiangjie.mysql.dao.BaseDao;
import com.baoxiangjie.mysql.impl.TeacherImpl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baoxiangjie.mysql.util.DatabaseUtils.*;

/**
 * sql语句方法实现
 * @author BaoXiangjie
 */
public class BaseDaoImpl implements BaseDao  {

    /**
     * 数据库连接
     * 预加载sql语句
     * 结果集
     * 获取sql语句
     */
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    SqlDaoImpl sqlDao =  new SqlDaoImpl();

    /**
     * 查询所有数据sql语句方法实现
     * @param clazz 类对象
     * @return 结果列表
     */
    @SuppressWarnings("all")
    @Override
    public <T> List<T> findAll(Class<?> clazz) {
        connection = getConnection();
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
            release(resultSet, statement, connection);
        }
        return null;
    }

    /**
     * 添加数据sql语句方法实现
     * @param clazz 类对象
     * @return 是否添加成功
     */
    @Override
    public boolean add(Class<?> clazz) {
        connection = getConnection();
        Object o = TeacherImpl.getInstance(clazz);
        Class<?> oClazz = o.getClass();
        Field[] oFields = oClazz.getDeclaredFields();
        try {
            String sql = sqlDao.addSql(clazz);
            statement = connection.prepareStatement(sql);
            int count = 0;
            for (Field oField : oFields) {
                oField.setAccessible(true);
                statement.setObject(++count, oField.get(o));
            }
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, statement, connection);
        }
        return false;
    }

    /**
     * 根据id删除数据
     * @param clazz 类对象
     * @param id 需要删除的对象的id
     * @return 是否删除成功
     */
    @Override
    public boolean delete(Class<?> clazz, Object id) {
        connection = getConnection();
        Id idAnnotation;
        Field[] fields = clazz.getDeclaredFields();
        try {
            StringBuilder sql = new StringBuilder(sqlDao.deleteSql(clazz));
            for (Field field : fields) {
                idAnnotation = field.getAnnotation(Id.class);
                if (idAnnotation != null) {
                    sql.append(idAnnotation.value()).append("=").append(id);
                    break;
                }
            }
            statement = connection.prepareStatement(sql.toString());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, statement, connection);
        }
        return false;
    }

    /**
     * 根据id查找数据
     * @param clazz 类对象
     * @param id 要查询的对象的id
     * @return 查找的数据
     */
    @SuppressWarnings("all")
    @Override
    public <T> List<T> findById(Class<?> clazz, Object id) {
        connection = getConnection();
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
            release(resultSet, statement, connection);
        }
        return null;
    }

    /**
     * 根据id修改数据
     * @param id 需要修改的对象的id
     * @return 是否修改成功
     */
    @Override
    public boolean update(Class<?> clazz, Object id) {
        System.out.println(findById(clazz, id));
        connection = getConnection();
        String sql = sqlDao.updateSql(clazz);
        try {
            statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(resultSet, statement, connection);
        }
        return false;
    }

}
