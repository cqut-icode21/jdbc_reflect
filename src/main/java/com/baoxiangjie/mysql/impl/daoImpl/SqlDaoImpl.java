package com.baoxiangjie.mysql.impl.daoImpl;

import com.baoxiangjie.mysql.annotation.Column;
import com.baoxiangjie.mysql.annotation.Id;
import com.baoxiangjie.mysql.annotation.Table;
import com.baoxiangjie.mysql.dao.SqlDao;

import java.lang.reflect.Field;

import static com.baoxiangjie.mysql.util.GetUpdateCondition.getCondition;

/**
 * 获取sql语句方法实现
 * @author BaoXiangjie
 */
public class SqlDaoImpl implements SqlDao {

    /**
     * 获取表中全部信息sql语句实现
     * @param clazz 类对象
     * @return 获取表中全部信息sql语句
     */
    @Override
    public String findAllSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.value();
            sql.append(columnName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.table_name();
        sql.append(tableName);
        return sql.toString();
    }

    /**
     * 获取添加信息sql语句实现
     * @param clazz 类对象
     * @return 添加信息sql语句
     */
    @Override
    public String addSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.table_name();
        sql.append(tableName).append(" (");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.value();
            sql.append(columnName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(") values (?,?,?,?,?)");
        return sql.toString();
    }

    /**
     * 获取删除信息sql语句实现
     * @param clazz 类对象
     * @return 删除信息sql语句
     */
    @Override
    public String deleteSql(Class<?> clazz) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.table_name();
        sql.append(tableName).append(" where ");
        return sql.toString();
    }

    /**
     * 获取通过id查找信息sql语句实现
     * @param clazz 类对象
     * @return 通过id查找信息sql语句
     */
    @Override
    public String findByIdSql(Class<?> clazz) {
        Id idAnnotation;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Column fieldAnnotation = field.getAnnotation(Column.class);
            String fieldName = fieldAnnotation.value();
            sql.append(fieldName).append(",");
        }
        sql.deleteCharAt(sql.length() - 1).append(" from ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.table_name();
        sql.append(tableName).append(" where ");
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.value();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }

    @Override
    public String updateSql(Class<?> clazz) {
        Id idAnnotation;
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.table_name();
        sql.append(tableName).append(" set ");
        String str = getCondition();
        sql.append(str).append(" where ");
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.value();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }
}
