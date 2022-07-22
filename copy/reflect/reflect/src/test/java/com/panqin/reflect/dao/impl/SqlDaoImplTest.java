package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

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