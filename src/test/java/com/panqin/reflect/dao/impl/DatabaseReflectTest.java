package com.panqin.reflect.dao.impl;

import com.gengxiangxi.reflect.dao.impl.DatabaseReflect;
import com.gengxiangxi.reflect.entities.Student;
import org.junit.Test;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "95001"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }
}