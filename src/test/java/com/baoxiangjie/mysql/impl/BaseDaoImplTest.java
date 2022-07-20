package com.baoxiangjie.mysql.impl;

import com.baoxiangjie.mysql.impl.daoImpl.BaseDaoImpl;
import com.baoxiangjie.mysql.pojo.Teacher;
import org.junit.Test;

/**
 * sql语句方法实现测试
 * @author BaoXiangjie
 */
public class BaseDaoImplTest {
    BaseDaoImpl baseDao = new BaseDaoImpl();

    @Test
    public void findAll() {
        baseDao.findAll(Teacher.class);
    }

    @Test
    public void add() {
        baseDao.add(Teacher.class);
    }

    @Test
    public void delete() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }
}