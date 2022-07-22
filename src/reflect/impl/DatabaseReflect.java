package reflect.impl;

import reflect.dao.BaseDao;
import reflect.utils.DatabaseUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code @description:} 实现SQL语句 及 增删改查方法
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
        List<T> results = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.findAllSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、执行SQL语句
            System.out.println("findAll:" + statement);
            resultSet = statement.executeQuery();
            // 遍历结果集
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    // 保证私有属性也能进行操作
                    field.setAccessible(true);
                    // 设置指定对象属性的值
                    field.set(t, resultSet.getObject(i + 1));
                }
                results.add((T) (t + "\n"));
            }
            return results;
        } catch (RuntimeException | SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
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
    public int delete(Class<?> clazz, Object id) {

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.deleteSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            statement.setObject(1, id);
            // 5、执行SQL语句
            System.out.println("delete:" + statement);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return 0;
    }

    /**
     * 新增
     *
     * @param objects 新增的对象
     * @return 是否成功
     */
    @Override
    public boolean add(Class<?> clazz, Object... objects) {

        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.addSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            // 4、填充参数（可选）
            System.out.println("add:" + statement);
            for (int i = 0; i < objects.length; i++) {
                statement.setObject(i + 1, objects[i]);
            }
            // 5、执行SQL语句
            int result = statement.executeUpdate();
            if (result > 0) {
                System.out.println("增加成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("增加失败");
        } finally {
            // 6、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
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
        Class<?> clazz = object.getClass();
        // 1、获取数据库连接
        connection = DatabaseUtil.getConnection();
        Field[] fields = clazz.getDeclaredFields();
        int result = 0,i=0;
        try {
            // 2、获取到SQL语句
            String sql = sqlDao.updateSql(clazz);
            // 3、预编译SQL语句
            statement = connection.prepareStatement(sql);
            for ( ; i < fields.length; i++) {
                Field field = fields[i];
                // 保证私有属性也能进行操作
                field.setAccessible(true);
                Object value = field.get(object);
                statement.setObject(i + 1, value);
            }
            statement.setObject( fields.length+ 1, id);
            // 4、执行SQL语句
            result = statement.executeUpdate();
            System.out.println("update:" + statement);

        } catch (RuntimeException | SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        } finally {
            // 7、释放资源
            DatabaseUtil.close(connection, statement, resultSet);
        }
        return result > 0;
    }
}
