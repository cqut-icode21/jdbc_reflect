package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DatabaseReflect2Test {
    DatabaseReflect2 databaseReflect2 = new DatabaseReflect2();
    @Test
    public void findAll() {
        System.out.println(databaseReflect2.findAll(Teacher.class));
    }

    @Test
    public void findById() {
        Teacher  teacher=new  Teacher("1","天王老总",25,"大学废物处理","男");
        System.out.println(databaseReflect2.findById(teacher.getClass(), "1"));

    }

    @Test
    public void delete() {
        System.out.println(databaseReflect2.delete(Teacher.class, "1"));
    }

    @Test
    public void add() {
        Teacher  teacher=new  Teacher("1","天王老总",25,"大学废物处理","男");
        System.out.println(databaseReflect2.add(teacher));
    }


    //修改
    @Test
    public void update() {
        Teacher teacher=new Teacher("5","天王",25,"大学废物处理","男");
        System.out.println(databaseReflect2.update(teacher,"5"));
    }
}