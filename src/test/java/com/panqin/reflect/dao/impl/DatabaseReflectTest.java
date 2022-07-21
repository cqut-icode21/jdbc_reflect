package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.SQLException;

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
        System.out.println(databaseReflect.findById(Student.class, "71"));
        System.out.println("\n");
    }

    @Test
    public void delete() {
        databaseReflect.delete(Student.class, "12");
        System.out.println("\n");
    }

    @Test
    public void add() throws IllegalAccessException {
        Student student1 = new Student("92","马冲",19);
        databaseReflect.add(student1);
        System.out.println();
    }
    @Test
    public void update() {
        Student student1 = new Student("71","苏腾",18);
        databaseReflect.update(student1,71);
        System.out.println();
    }
}