package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "1"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
    }

    @Test
    public void add() {
        Student teacher = new Student(3,"王","女","23");
        if (databaseReflect.add(teacher)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        System.out.println("\n");
    }

    @Test
    public void update() {
    }
}