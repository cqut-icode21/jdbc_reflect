package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;

import java.util.List;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("___________----------____________");
        System.out.println("|          1 添加数据             |");
        System.out.println("|          2 修改数据             |");
        System.out.println("|          3 删除数据             |");
        System.out.println("|          4 查询数据             |");
        System.out.println("|          5 查询所有             |");
        System.out.println("___________----------____________");

        System.out.print("输入:");
        int i = input.nextInt();

        System.out.println("类型:");
        System.out.println("            1 学生");
        System.out.println("            2 老师");

        System.out.print("选择要操作的数据类型:");
        int n = input.nextInt();

        switch (i){
            case 1:
                if (n == 1){
                    addStudent();
                }else if (n == 2){
                    addTeacher();
                }
                break;
            case 2:
                if (n == 1){
                    updateStudent();
                }else if (n == 2){
                    updateTeacher();
                }
                break;
            case 3:
                if (n == 1){
                    deleteStudent();
                } else if (n == 2) {
                    deleteTeacher();
                }
                break;
            case 4:
                if (n == 1){
                    findStudent();
                } else if (n == 2) {
                    findTeacher();
                }
            case 5:
                if (n == 1){
                    findAllStudent();
                } else if (n == 2) {
                    findAllTeacher();
                }
        }

        String[] a = null;
        main(a);
    }

    public static void addStudent(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();


        System.out.print("学号:");
        String sno = input.next();

        System.out.print("姓名:");
        String name = input.next();

        System.out.print("年龄:");
        int age = input.nextInt();

        System.out.print("性别:");
        String sex = input.next();

        System.out.print("系别:");
        String sdept = input.next();

        Student student = new Student(sno,name,age,sex,sdept);
        if (databaseReflect.add(student)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
            System.out.println("\n");

            System.out.println("重新添加！");
            addStudent();
        }
        System.out.println("\n");

    }

    public static void addTeacher(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();


        System.out.print("教工号:");
        int tno = input.nextInt();

        System.out.print("姓名:");
        String name = input.next();

        System.out.print("年龄:");
        int age = input.nextInt();

        System.out.print("性别:");
        String sex = input.next();


        Teacher teacher = new Teacher(tno,name,sex,age);
        if (databaseReflect.add(teacher)){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");

            System.out.println("\n");
            System.out.println("重新添加！");
            addTeacher();
        }
        System.out.println("\n");
    }

    public static void updateStudent(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("修改人的学号:");
        String id = input.next();

        System.out.print("修改后的姓名:");
        String name = input.next();

        System.out.print("修改后的年龄:");
        int age = input.nextInt();

        System.out.print("修改后的性别:");
        String sex = input.next();

        System.out.print("修改后的系别:");
        String sdept = input.next();

        Student student = new Student(id,name,age,sex,sdept);
        if (databaseReflect.update(student,id)){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");

            System.out.println("重新修改！");
            updateStudent();
        }
        System.out.println("\n");
    }

    public static void updateTeacher(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("修改人的教工号:");
        String id = input.next();

        System.out.print("修改后的姓名:");
        String name = input.next();

        System.out.print("修改后的年龄:");
        int age = input.nextInt();

        System.out.print("修改后的性别:");
        String sex = input.next();


        Teacher teacher = new Teacher(Integer.parseInt(id),name,sex,age);
        if (databaseReflect.update(teacher,id)){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");

            System.out.println("重新修改！");
            updateTeacher();
        }
        System.out.println("\n");
    }

    public static void deleteStudent(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("要删除学生的学号:");
        String id = input.next();
        if (databaseReflect.delete(Student.class, id)){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        System.out.println("\n");
    }

    public static void deleteTeacher(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("要删除老师的教工号:");
        String id = input.next();
        if (databaseReflect.delete(Teacher.class, id)){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        System.out.println("\n");
    }

    public static void findStudent(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("要查找的学生的学号:");
        String id = input.next();

        System.out.println(databaseReflect.findById(Student.class, id));
        System.out.println("\n");
    }

    public static void findTeacher(){
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();

        System.out.print("要查找的老师的教工号:");
        String id = input.next();

        System.out.println(databaseReflect.findById(Teacher.class, id));
        System.out.println("\n");
    }

    public static void findAllStudent(){
        DatabaseReflect databaseReflect = new DatabaseReflect();

        List<Student> list = databaseReflect.findAll(Student.class);

        for (Student student : list) {
            System.out.println(student);
        }
        System.out.println("\n");
    }

    public static void findAllTeacher(){
        DatabaseReflect databaseReflect = new DatabaseReflect();

        List<Teacher> list = databaseReflect.findAll(Teacher.class);

        for (Teacher teacher : list) {
            System.out.println(teacher);
        }
        System.out.println("\n");
    }
}
