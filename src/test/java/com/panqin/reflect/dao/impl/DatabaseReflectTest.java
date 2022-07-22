package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll(Student.class));
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "1"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "1"));
        System.out.println("\n");
    }

    @Test
    public void add() {
        Student student=new Student("12","谢海燕","女","软件工程",19);
        System.out.println(databaseReflect.add(student));
        System.out.println("\n");
    }

    @Test
    public void update() {
        Student student=(Student) databaseReflect.findById(Student.class,"2");
        student.setName("李斯");
        System.out.println(databaseReflect.update(student,"2"));
        System.out.println("\n");
    }
}