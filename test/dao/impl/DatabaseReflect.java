package test.dao.impl;

import test.connect.DatabaseConnect;
import test.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

import static test.connect.DatabaseConnect.getConnection;

/**
 * @author 龙
 */
public class DatabaseReflect implements BaseDao {
    /**
     * 数据库连接
     */
    Connection connection = getConnection();
    /**
     * 预编译
     */
    PreparedStatement preparedStatement;
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
     */
    @Override
    public <T> void findAll(Class<T> clazz) {
        String sql = sqlDao.findAllSql(clazz);
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("sid");
                String name = resultSet.getString("sname");
                String age = resultSet.getString("sage");
                System.out.println("id: " + id + "  name: " + name + "  age: " + age);
            }
            DatabaseConnect.close(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据id查询
     *
     * @param clazz 类
     * @param id1    要查询的对象的id
     */
    @Override
    public void findById(Class<?> clazz, Object id1) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你想查询的id");
        String nu;
        nu = input.nextLine();
        String sql = sqlDao.findByIdSql(clazz) +"'"+nu+"'"+";";
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String id = resultSet.getString("sid");
            String name = resultSet.getString("sname");
            String age = resultSet.getString("sage");
            System.out.println("id: "+id+"  name: "+name+"  age: "+age);
            DatabaseConnect.close(connection,preparedStatement,resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除
     *
     * @param clazz 类
     * @param id1   需要删除的对象的id
     */
    @Override
    public void delete(Class<?> clazz, Object id1) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你想删除的数据id");
        String id = input.nextLine();
        String sql = sqlDao.deleteSql(clazz)+"'"+id+"'"+";";
        System.out.println(sql);
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            int i = preparedStatement.executeUpdate();
            System.out.println("删除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        Scanner input = new Scanner(System.in);
        String sqlSno;
        String sqlSname;
        String sqlAge;
        System.out.println("请输入ID号");
        sqlSno = input.nextLine();
        System.out.println("请输入姓名");
        sqlSname = input.nextLine();
        System.out.println("请输入年龄");
        sqlAge = input.nextLine();
        String q = "("+"'"+sqlSno+"'"+","+"'"+sqlSname+"'"+","+"'"+sqlAge+"'"+");";
        String string = sqlDao.addSql(object.getClass()) + q;
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(string);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            int i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改
     *
     * @param object 对象
     * @param id     需要修改的对象的id
     */
    @Override
    public void update(Object object, Object id) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你想修改的内容");
        String str;
        String sql;
        String data;
        while (true) {
            str = input.nextLine();
            if (Objects.equals(str, "sid")) {
                sql = "sid";
                System.out.println("请输入具体对应ID");
                data = input.nextLine();
                break;
            }else if (Objects.equals(str,"sname")) {
                sql = "sname";
                System.out.println("请输入具体对应姓名");
                data = input.nextLine();
                break;
            }else if (Objects.equals(str,"sage")) {
                sql = "sage";
                System.out.println("请输入具体对应年龄");
                data = input.nextLine();
                break;
            }else {
                System.out.println("输入有误请重新输入");
            }
        }
        String string = sqlDao.updateSql(object.getClass()) + sql + " = ";
        System.out.println("输入数据");
        String p = input.nextLine();
        String sql0 ;
        sql0 = string +"'"+p+"'"+" WHERE "+sql+" = "+data+ ";";
        System.out.println(sql0);
        connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
