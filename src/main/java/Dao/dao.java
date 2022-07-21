package Dao;

import com.mysql.cj.jdbc.Driver;
import note.Table;

import java.lang.reflect.Field;
import java.sql.*;

public class dao {
    private static Connection connection  = null;

    private static PreparedStatement pre = null;

    private static ResultSet resultSet = null;

    public static <T> void addOne(T object,Table table) throws SQLException, IllegalAccessException {
        connection = ConnectionUtils.setConnection();

        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields();
        int len = fields.length;

        String tableName =  table.tableName();
        StringBuilder sb = new StringBuilder();
        sb.append("insert into person.").append(tableName).append(" values (");
        for (int i = 0; i < len; i++) {
            if(i!=4){
                sb.append(" ?,");
            }else{
                sb.append(" ?");
            }
        }
        sb.append(")");
        pre = connection.prepareStatement(sb.toString());

        for (int i = 0; i < len; i++) {
            fields[i].setAccessible(true);
            pre.setObject(i+1,fields[i].get(object));
        }
        pre.executeUpdate();

        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

    public static void deleteOne(String id,Table table) throws SQLException {
        String tableName = table.tableName();

        connection = ConnectionUtils.setConnection();
        StringBuilder sb = new StringBuilder();
        if(tableName.equals("student")){
            sb.append("delete from person.").append(tableName).append(" where stu_id = ?");
        }else{
            sb.append("delete from person.").append(tableName).append(" where te_id = ?");
        }
        pre = connection.prepareStatement(sb.toString());
        pre.setObject(1,id);

        pre.executeUpdate();
        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

    public static <T> void selectOne(String id,Table table) throws SQLException {
        String tableName = table.tableName();

        connection = ConnectionUtils.setConnection();
        StringBuilder sb = new StringBuilder();
        if(tableName.equals("student")){
            sb.append("select * from person.").append(tableName).append(" where stu_id = ?");
        }else{
            sb.append("select * from person.").append(tableName).append(" where te_id = ?");
        }
        boolean charge2 = charge(sb.toString(),id);

        pre = connection.prepareStatement(sb.toString());
        pre.setObject(1,id);

        resultSet = pre.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int cnt = resultSetMetaData.getColumnCount();

        if(!charge2){
            System.out.println("无该人员!");
        }else{
            System.out.println("该人员是：");
            while(resultSet.next()){
                for (int i = 0; i < cnt; i++) {
                    System.out.print(resultSet.getObject(i+1)+" ");
                }
                System.out.println();
            }
        }

        ConnectionUtils.closeAll(connection,pre,resultSet);
    }
    public static boolean charge(String sql,String id) throws SQLException {
        PreparedStatement temp = connection.prepareStatement(sql);
        temp.setObject(1,id);
        ResultSet temp1 = temp.executeQuery();
        boolean ans = temp1.next();
        temp.close();
        temp1.close();
        return ans;
    }
    public static void selectAll(Table table) throws SQLException {
        String tableName = table.tableName();

        connection = ConnectionUtils.setConnection();
        String sql = String.format("select * from person.%s", tableName);

        pre = connection.prepareStatement(sql);
        resultSet = pre.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int cnt = resultSetMetaData.getColumnCount();
        if(cnt==0){
            System.out.println("人员库为空请添加数据！");
        }else{
            while(resultSet.next()){
                for (int i = 0; i < cnt; i++) {
                    System.out.print(resultSet.getObject(i+1)+" ");
                }
                System.out.println();
            }
            System.out.println("查询完成！");
        }
        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

}
