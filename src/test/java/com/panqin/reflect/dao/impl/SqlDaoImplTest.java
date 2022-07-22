package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    @Test
    public void findAllSql() {
        sqlDao.findAllSql(Student.class);
//        sqlDao.findAllSql(Teacher.class);
    }

    @Test
    public void findByIdSql() {
        sqlDao.findByIdSql(Student.class);
//        sqlDao.findByIdSql(Teacher.class);
    }

    @Test
    public void deleteSql() {
        sqlDao.deleteSql(Student.class);
//        sqlDao.deleteSql(Teacher.class);
    }

    @Test
    public void addSql() {
        sqlDao.addSql(Student.class);
//        sqlDao.addSql(Teacher.class);
    }

    @Test
    public void updateSql() {
        sqlDao.updateSql(Student.class);
//        sqlDao.updateSql(Teacher.class);
    }
}