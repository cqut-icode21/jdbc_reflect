package com.panqin.reflect.dao.impl;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;
import com.panqin.reflect.dao.SqlDao;
import com.panqin.reflect.entities.Student;

import java.lang.reflect.Field;

/**
 * @author 潘琴
 * @date:2021/8/2
 * @description: 动态拼接SQL语句
 */
public class SqlDaoImpl implements SqlDao {

    /**
     * 查询所有的SQL语句
     *
     * @param clazz 类
     * @return 查询所有的SQL语句字符串:select sid,sname,sage from student
     */
    @Override
    public String findAllSql(Class<?> clazz) {
        // SQL语句
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName);
        return sql.toString();
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @return 根据id查询的SQL语句字符串:select sid,sname,sage from student where sid = ?
     */
    @Override
    public String findByIdSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where ");

        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @return 删除的SQL语句字符串
     */
    @Override
    public String deleteSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("delete ");

        // 获取类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        sql.append(" from ");

        // 获取类中注解的表名
        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" where ");

        // 获取字段id
        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }


    /**
     * 新增
     *
     * @param clazz 类
     * @return 新增的SQL语句
     */
    @Override
    public String addSql(Class<?> clazz) {
        Student student=new Student();
        StringBuilder sql = new StringBuilder();
        sql.append("insert ");
        sql.append(" into ");
        sql.append(clazz.getSimpleName());
        sql.append("(");

        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append("values");
        sql.append("(");

       for (int i=0;i< fields.length;i++){
                sql.append("?" + ",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();

    }

    /**
     * 修改
     *
     * @param clazz 类
     * @return 修改的SQL语句
     */
    @Override
    public String updateSql(Class<?> clazz) {
        Id idAnnotation = null;
        StringBuilder sql = new StringBuilder();
        sql.append("update ");
        System.out.println("dui");

        Table tableAnnotation = (Table) clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.tableName();
        sql.append(tableName).append(" set ");


       Field[] fields = clazz.getDeclaredFields();
        // 遍历属性，获取类中注解的字段、表名
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.columnName();
            // 将字段拼接到SQL语句
            sql.append(columnName).append("= ?").append(",");
        }
        // 删除SQL语句中最后多余的逗号，
        sql.deleteCharAt(sql.length() - 1);
         sql.append(" WHERE ");

        for (Field field : fields) {
            idAnnotation = field.getAnnotation(Id.class);
            if (idAnnotation != null) {
                String idName = idAnnotation.idName();
                sql.append(idName).append(" = ?");
                break;
            }
        }
        return sql.toString();
    }
}
