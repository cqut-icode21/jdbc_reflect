package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

public class TeacherTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        System.out.println(databaseReflect.findAll(Teacher.class));
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Teacher.class, "1"));
        System.out.println("\n");
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Teacher.class, "1"));
        System.out.println("\n");
    }

    @Test
    public void add() {
        Teacher teacher=new Teacher("12","信海","男","数学",32);
        System.out.println(databaseReflect.add(teacher));
        System.out.println("\n");
    }

    @Test
    public void update() {
        Teacher teacher=(Teacher) databaseReflect.findById(Teacher.class,"2");
        teacher.setName("李斯");
        System.out.println(databaseReflect.update(teacher,"2"));
        System.out.println("\n");
    }
}
