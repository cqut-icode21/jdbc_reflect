package example.第三次作业.annotation.processor;

import example.common.ConnectionUtil;
import example.第三次作业.annotation.dao.Transaction;
import example.第三次作业.dao.impl.CRUDIml;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author LIXIN
 */
public class TransactionProcessor {
    public Object process(Object o, MethodProxy methodProxy, Method method, Object[] args) {
        Object x = null;
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Transaction annotation = method.getAnnotation(Transaction.class);
        boolean autoCloseConnection = annotation.autoCloseConnection();
        Class<?> clazz = o.getClass().getSuperclass();
        try {
            CRUDIml<Object> o1 = (CRUDIml) o;
            Field connectionField = clazz.getDeclaredField("connection");
            connectionField.setAccessible(true);
            connectionField.set(o1, connection);
            System.out.println("注入连接完毕");
            x = methodProxy.invokeSuper(o, args);
            System.out.println("执行方法完毕");
            connection.commit();
        } catch (Throwable e) {
            e.printStackTrace();

            try {
                System.err.println("发生异常 开始回滚");
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (autoCloseConnection) {
            close(o);
        }
        return x;
    }

    private void close(Object o) {
        CRUDIml o1 = (CRUDIml) o;
        try {
            Class<?> clazz = o.getClass().getSuperclass();
            Field preparedStatement = clazz.getDeclaredField("preparedStatement");
            preparedStatement.setAccessible(true);
            Field resultSet = clazz.getDeclaredField("resultSet");
            resultSet.setAccessible(true);
            Field connection = clazz.getDeclaredField("connection");
            connection.setAccessible(true);
            PreparedStatement preparedStatement1 = (PreparedStatement) preparedStatement.get(o1);
            ResultSet resultSet1 = (ResultSet) resultSet.get(o1);
            Connection connection1 = (Connection) connection.get(o1);
            ConnectionUtil.close(connection1, preparedStatement1, resultSet1);
            System.out.println("自动关闭连接成功");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
