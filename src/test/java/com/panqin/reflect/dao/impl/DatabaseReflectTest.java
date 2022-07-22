package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
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
        System.out.println(databaseReflect.findById(Student.class, "10086"));
        System.out.println("\n");
    }

    @Test
    public void delete() {
        if (databaseReflect.delete(Student.class,"15577"))
            System.out.println("删除成功");
        else System.out.println("删除失败");
        System.out.println(databaseReflect.findAll(Student.class));
    }

    @Test
    public void add() {
        Student xiao=new Student("15577","王明",21);
        if (databaseReflect.add(xiao)){
            System.out.println("添加成功");
            System.out.println(databaseReflect.findAll(Student.class));
        }
        else System.out.println("添加失败");
    }

    @Test
    public void update() {
        Student gai=new Student("15577","王明",22);
        if (databaseReflect.update(gai,"15577"))
            System.out.println("修改成功");
        else System.out.println("修改失败");
    }
}