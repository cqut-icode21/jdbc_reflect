package com.baoxiangjie.mysql.impl;

import com.baoxiangjie.mysql.impl.daoImpl.SqlDaoImpl;
import com.baoxiangjie.mysql.pojo.Teacher;
import org.junit.Test;

/**
 * SqlDao接口实现测试
 * @author BaoXiangjie
 */
public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    @Test
    public void testFindAllSql() {
        System.out.println(sqlDao.findAllSql(Teacher.class));
    }

    @Test
    public void testAddSql() {
        System.out.println(sqlDao.addSql(Teacher.class));
    }

    @Test
    public void testDeleteSql() {
        System.out.println(sqlDao.deleteSql(Teacher.class));
    }

    @Test
    public void testFindByIdSql() {
        System.out.println(sqlDao.findByIdSql(Teacher.class));
    }

    @Test
    public void testUpdateSql() {
        System.out.println(sqlDao.updateSql(Teacher.class));
    }
}