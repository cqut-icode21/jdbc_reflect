package com.zx.reflect.dao.impl;

import com.zx.reflect.dao.BaseDao;
import com.zx.reflect.entities.Student;
import com.zx.reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.zx.reflect.utils.DatabaseUtil.*;


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
        List<T> list = new ArrayList<>();
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.findAllSql(clazz);
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getInt(3));
                list.add((T) student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DatabaseUtil.close(connection, statement, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
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
            close(connection, statement, resultSet);
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

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection, statement, resultSet);
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
        Field[] fields = object.getClass().getDeclaredFields();
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.addSql(object.getClass());
            statement = connection.prepareStatement(sql);

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object info = fields[i].get(object);
                statement.setObject(i + 1, info);
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection, statement, resultSet);
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
        Object obj = findById(object.getClass(), id);
        Field[] fields = object.getClass().getDeclaredFields();
        connection = DatabaseUtil.getConnection();
        try {
            String sql = sqlDao.updateSql(object.getClass());
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                Object info = fields[i].get(object);
                if (info != null && !"-1".equals(String.valueOf(info))) {
                    statement.setObject(i + 1, info);
                } else {
                    statement.setObject(i + 1, fields[i].get(obj));
                }
                statement.setObject(4, id);
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }
}
