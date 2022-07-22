package example.第三次作业.dao.impl;

import example.common.ConnectionUtil;
import example.第三次作业.Entity.Condition;
import example.第三次作业.annotation.dao.Table;
import example.第三次作业.annotation.dao.Transaction;
import example.第三次作业.dao.BaseCRUD;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author LIXIN
 */
public class CRUDIml<T> extends BaseCRUD<T, BaseSQLImpl<T>> {
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;
    protected Connection connection = null;

    private void writeInfo(Object object, String sql) {
        connection = ConnectionUtil.getConnection();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            preparedStatement = connection.prepareStatement(sql);
            int count = 0;
            for (Field field : fields) {
                field.setAccessible(true);
                Object o = field.get(object);
                if (o != null) {
                    preparedStatement.setObject(count + 1, o);
                    count++;
                }
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BaseSQLImpl<T> getSql() {
        return new BaseSQLImpl<>();
    }

    @Transaction
    @Override
    public boolean updateOne(T entity, Condition condition) {
        String updateSql = null;
        try {
            updateSql = getSql().updateOne(entity, condition);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        writeInfo(entity, updateSql);
        try {
            int count = preparedStatement.getParameterMetaData().getParameterCount();
            preparedStatement.setObject(count, condition.getValue());
            System.out.println(preparedStatement);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transaction(autoCloseConnection = true)
    @Override
    public boolean saveOne(T entity) {
        Class<?> clazz = entity.getClass();
        try {
            if (!hasTable(connection, clazz)) {
                String table = getSql().createTable(clazz);
                preparedStatement = connection.prepareStatement(table);
                preparedStatement.execute();
                System.out.println("--------正在创建表--------");
                System.out.println(table);
                System.out.println("--------创建成功--------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String updateSql = getSql().saveOne((Class<T>) clazz);
        // updateSql =insert into e_student(stu_id,name,gender,age,major,classes,college) values(?,?,?,?,?,?,?)
        System.out.println(updateSql);
        writeInfo(entity, updateSql);
        System.out.println(preparedStatement);
//        com.mysql.cj.jdbc.ClientPreparedStatement: insert into e_student(stu_id,name,gender,age,major,classes,college) values('1234323423','类型','女',23,'33',3,'34')
        try {
            int i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transaction
    @Override
    public int deleteOne(Class<T> clazz, Condition conditions) {
        String deleteSql = getSql().deleteOne(clazz, conditions.getPrepared());
        try {
            preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setObject(1, conditions.getValue());
            System.out.println(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Transaction
    @Override
    public List<T> query(Class<T> clazz, Condition condition) {
        List<T> results = new ArrayList<>();
        String allSql = getSql().query(clazz, condition.getPrepared());
        Field[] fields = clazz.getDeclaredFields();
        try {
            preparedStatement = connection.prepareStatement(allSql);
            preparedStatement.setObject(1, condition.getValue());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(t, resultSet.getObject(i + 1));
                }
                results.add(t);
            }
            return results;
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean hasTable(Connection condition, Class<?> clazz) {
        try {
            Table annotation = clazz.getAnnotation(Table.class);
            if (annotation == null) {
                throw new Exception(clazz.getName() + ":不存在表名注解");
            }
            String tableName = annotation.value();//e_student

            String query = "select TABLE_NAME t from information_schema.TABLES where TABLE_NAME ='" + tableName + "'";
            PreparedStatement preparedStatement = condition.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
