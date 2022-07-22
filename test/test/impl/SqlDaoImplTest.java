package test.test.impl;

import org.junit.Test;
import test.dao.impl.SqlDaoImpl;

/**
 * @author 龙
 */
public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();
    Object o;
    public void setO(Object ob) {
        o = ob;
    }
    @Test
    public void findAllSql() {
        System.out.println(sqlDao.findByIdSql(o.getClass()));
        }

    @Test
    public void findByIdSql() {
        System.out.println(sqlDao.findAllSql(o.getClass()));
    }

    @Test
    public void deleteSql() {
        System.out.println(sqlDao.deleteSql(o.getClass()));
    }

    @Test
    public void addSql() {
        System.out.println(sqlDao.addSql(o.getClass()));
    }

    @Test
    public void updateSql() {
        System.out.println(sqlDao.updateSql(o.getClass()));
    }
}