package com.zx.reflect.dao.impl;

import com.zx.reflect.entities.Student;
import com.zx.reflect.entities.Teacher;
import org.junit.Test;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll((Student.class)));
    }

    @Test
    public void findById() {
//        System.out.println(databaseReflect.findById(Student.class, "95002"));
//        System.out.println("\n");
        System.out.println(databaseReflect.findById(Teacher.class, "10006"));
    }

    @Test
    public void delete() {
        if (databaseReflect.delete(Student.class,"95002")){
            findAll();
        }
    }

    @Test
    public void add() {
        Student student = new Student("95004","Mark",19);
        if (databaseReflect.add(student)){
            findAll();
        }
    }

    @Test
    public void update() {
        Student student = new Student("95003","李华",22);
        if (databaseReflect.update(student,95003)){
            findAll();
        }
    }
}