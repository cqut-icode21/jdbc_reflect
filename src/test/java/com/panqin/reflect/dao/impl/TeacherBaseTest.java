package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import org.junit.Test;

import java.util.List;

public class TeacherBaseTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        List<Teacher> list = databaseReflect.findAll(Teacher.class);
        for (Teacher student : list){
            System.out.println(student);
        }
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Teacher.class, "89"));
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Teacher.class, "1001"));
        System.out.println("\n");
    }

    @Test
    public void add() {
        Teacher teacher = new Teacher("89","某魔",19,"男","英语");
        System.out.println(databaseReflect.add(teacher));
    }

    @Test
    public void update() {
        Teacher student = (Teacher) databaseReflect.findById(Teacher.class, "1001");
        student.setName("某某某");
        databaseReflect.update(student,"1001");
    }
}
