package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll(Student.class));
        System.out.println("\n");
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "2"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "14"));
        System.out.println("\n");
    }

    @Test
    public void add() {
//        Student student = new Student("14","tyew",21);
        Teacher teacher = new Teacher("14","小红",68);
        System.out.println(databaseReflect.add(teacher));
        System.out.println("\n");

    }

    @Test
    public void update() {
        Student student = new Student("13","tyew",21);
        System.out.println(databaseReflect.update(student, "1"));
        System.out.println("\n");
    }
}