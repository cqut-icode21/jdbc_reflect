package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll(Student.class));
    }

    @Test
    public void findById() {
        Student st=new Student();
        System.out.println(databaseReflect.findById(st.getClass(), "1"));

    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "1"));
    }

    @Test
    public void add() {
        Student student=new Student("1","天王",25,"大学废物处理","男");
        System.out.println(databaseReflect.add(student));
    }


    //修改
    @Test
    public void update() {
        Student student=new Student("5","天王",25,"大学废物处理","男");
        System.out.println(databaseReflect.update(student,"5"));
    }
}