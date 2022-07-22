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
        System.out.println(databaseReflect.findById(Teacher.class, "1"));
        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Student.class, "1"));
//        System.out.println("\n");
//        System.out.println(databaseReflect.findById(Teacher.class, "10101"));
    }

    @Test
    public void delete() {
        if (databaseReflect.delete(Teacher.class, 2)){
            System.out.println("success！");
        }else {
            System.out.println("defeat！");
        }
        System.out.println("\n");
//        if (databaseReflect.delete(Student.class, "12")){
//            System.out.println("success！");
//        }else {
//            System.out.println("defeat！");
//        }
//        System.out.println("\n");
    }

    @Test
    public void add() {
        Teacher teacher = new Teacher(2,"旺旺","女",25);
        if (databaseReflect.add(teacher)){
            System.out.println("success！");
        }else {
            System.out.println("defeat！");
        }
        System.out.println("\n");
//        Student student = new Student("12","旺旺",25,"女","计算机系");
//        if (databaseReflect.add(student)){
//            System.out.println("success！");
//        }else {
//            System.out.println("defeat！");
//        }
//        System.out.println("\n");
    }

    @Test
    public void update() {
        Teacher teacher = new Teacher(2,"汪汪","女",25);
        if (databaseReflect.update(teacher,2)){
            System.out.println("success！");
        }else {
            System.out.println("defeat！");
        }

        System.out.println("\n");
    }

//        Student student = new Student("12","旺旺",25,"女","软件工程系");
//        if (databaseReflect.update(student,"12")){
//            System.out.println("success！");
//        }else {
//            System.out.println("defeat！");
//        }
//
//        System.out.println("\n");
//    }

}