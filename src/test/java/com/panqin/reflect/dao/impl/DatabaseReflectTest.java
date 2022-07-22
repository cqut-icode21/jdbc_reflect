package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        DatabaseReflect databaseReflect = new DatabaseReflect();

//        List<Student> list = databaseReflect.findAll(Student.class);
//
//        for (Student student : list) {
//            System.out.println(student);
//        }
//        System.out.println("\n");

        List<Teacher> list = databaseReflect.findAll(Teacher.class);

        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
    }

    @Test
    public void findById() {
//        System.out.println(databaseReflect.findById(Student.class, "1"));
//        System.out.println("\n");
        System.out.println(databaseReflect.findById(Teacher.class, "4"));
    }

    @Test
    public void delete() {
//        if (databaseReflect.delete(Student.class, "11")){
//            System.out.println("删除成功！");
//        }else {
//            System.out.println("删除失败！");
//        }
//        System.out.println("\n");

        if (databaseReflect.delete(Teacher.class, "4")){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        System.out.println("\n");
    }

    @Test
    public void add() {
//        Student student = new Student("12","小芳",21,"女","数学系");
//        if (databaseReflect.add(student)){
//            System.out.println("添加成功！");
//        }else {
//            System.out.println("添加失败！");
//        }
//        System.out.println("\n");

        Teacher teacher = new Teacher(4,"王老师","女",34);
        if (databaseReflect.add(teacher)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        System.out.println("\n");
    }

    @Test
    public void update() {
//        Student student = new Student("11","小芳",21,"女","软件工程系");
//        if (databaseReflect.update(student,"11")){
//            System.out.println("修改成功！");
//        }else {
//            System.out.println("修改失败！");
//        }
//
//        System.out.println("\n");

        Teacher teacher = new Teacher(4,"王老师","女",21);
        if (databaseReflect.update(teacher,"4")){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }

        System.out.println("\n");
    }
}