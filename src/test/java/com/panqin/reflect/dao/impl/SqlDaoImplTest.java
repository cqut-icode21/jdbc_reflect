package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    @Test
    public void findAllSql() {
        sqlDao.findAllSql(Student.class);
    }

    @Test
    public void findByIdSql() {
        sqlDao.findByIdSql(Student.class);
//        sqlDao.findByIdSql(Teacher.class);
    }

    @Test
    public void deleteSql() {
        sqlDao.deleteSql(Student.class);
    }

    @Test
    public void addSql() {
        sqlDao.addSql(Student.class);
    }

    @Test
    public void updateSql() {
        sqlDao.updateSql(Student.class);
    }
}