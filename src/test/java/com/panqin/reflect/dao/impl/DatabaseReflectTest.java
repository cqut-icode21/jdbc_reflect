package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll(Student.class));
        System.out.println("\n");

    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "3"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "3"));
        System.out.println("\n");
    }

    @Test
    public void add() {

       Student Student=new Student();
        System.out.println(databaseReflect.add(Student));
        System.out.println("\n");

    }

    @Test
    public void update() {
        Student student=new Student();
        System.out.println(databaseReflect.update(student, "2"));
        System.out.println("\n");
    }
}