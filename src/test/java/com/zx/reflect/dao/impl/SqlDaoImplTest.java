package com.zx.reflect.dao.impl;

import com.zx.reflect.entities.Student;
import org.junit.Test;

public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    @Test
    public void findAllSql() {
    }

    @Test
    public void findByIdSql() {
        sqlDao.findByIdSql(Student.class);
//        sqlDao.findByIdSql(Teacher.class);
    }

    @Test
    public void deleteSql() {
    }

    @Test
    public void addSql() {
    }

    @Test
    public void updateSql() {
    }
}