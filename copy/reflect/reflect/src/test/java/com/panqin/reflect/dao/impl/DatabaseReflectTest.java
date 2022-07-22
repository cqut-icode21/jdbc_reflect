package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
    }

    @Test
    public void findById() throws Exception {
        System.out.println(databaseReflect.findById(Teacher.class, "3"));
    }

    @Test
    public void delete() {

        if (databaseReflect.delete(Teacher.class, "4")){
            System.out.println("已经删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        System.out.println("\n");
    }

    @Test
    public void add() {
        Teacher teacher = new Teacher(6,"王老师","女",34);
        if (databaseReflect.add(teacher)){
            System.out.println("已经添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        System.out.println("\n");
    }

    @Test
    public void update() {
        Teacher teacher = new Teacher(3,"王老师","女",21);
        if (databaseReflect.update(teacher,"3")){
            System.out.println("已经修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        System.out.println("\n");
    }
}