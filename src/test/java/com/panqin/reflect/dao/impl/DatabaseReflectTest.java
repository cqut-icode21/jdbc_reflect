package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import java.util.List;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        List<Student> list = databaseReflect.findAll(Student.class);
        for (Student student : list){
            System.out.println(student);
        }
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "11"));
        System.out.println("\n");
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "89"));
    }

    @Test
    public void add() {
        Student student = new Student("66","某",19,"男","英语");
        System.out.println(databaseReflect.add(student));
    }

    @Test
    public void update() {
        Student student = (Student) databaseReflect.findById(Student.class, "88");
        student.setName("某某某");
        databaseReflect.update(student,"88");
    }
}