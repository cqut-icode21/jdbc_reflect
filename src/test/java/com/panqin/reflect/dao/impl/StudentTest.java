package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;

import java.util.Scanner;

public class StudentTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabaseReflect dr = new DatabaseReflect();
        Student student = new Student();

        while (true) {
            System.out.println("   1.查看信息");
            System.out.println("   2.查询信息");
            System.out.println("   3.添加信息");
            System.out.println("   4.修改信息");
            System.out.println("   5.删除信息");
            System.out.println("     ");
            System.out.println("请输入选择的序号：");

            int a = sc.nextInt();
            //显示所有信息
            if (a == 1) {
                Object findAll = dr.findAll(Student.class);
                System.out.println(findAll);
            }
            //根据id查询
            else if (a == 2) {
                System.out.println("请输入学号");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("学号不正确，请重新输入");
                    String idd = sc.next();
                    Object findId = dr.findById(Student.class, idd);
                    System.out.println(findId);

                } else {
                    Object findId = dr.findById(Student.class, id);
                    System.out.println(findId);
                }
            }
            //添加信息
            else if (a == 3) {
                System.out.println("请输入添加学生信息");
                System.out.println("请输入学生学号：");
                String id = sc.next();

                if (id.length() != 11) {
                    System.out.println("学号不正确，请重新输入");
                    String idd = sc.next();
                    System.out.println("请输入学生姓名：");
                    String name = sc.next();
                    System.out.println("请输入学生年龄：");
                    Integer age = sc.nextInt();
                    student = new Student(idd, name, age);

                    Object add = dr.add(student);
                    System.out.println(student);
                    System.out.println(add);
                } else {
                    System.out.println("请输入学生姓名：");
                    String name = sc.next();
                    System.out.println("请输入学生年龄：");
                    Integer age = sc.nextInt();
                    student = new Student(id, name, age);

                    Object add = dr.add(student);
                    System.out.println(student);
                    System.out.println(add);
                }
            }
            //修改信息
            else if (a == 4) {
                System.out.println("请输入以下信息");
                System.out.println("请输入学生学号：");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("学号不正确，请重新输入");
                    String idd = sc.next();
                    System.out.println("请输入学生姓名：");
                    String name = sc.next();
                    System.out.println("请输入学生年龄：");
                    Integer age = sc.nextInt();
                    student = new Student(id, name, age);

                    Object update = dr.update(student, idd);
                    System.out.println(update);

                } else {
                    System.out.println("请输入学生姓名：");
                    String name = sc.next();
                    System.out.println("请输入学生年龄：");
                    Integer age = sc.nextInt();
                    student = new Student(id, name, age);

                    Object update = dr.update(student, id);
                    System.out.println(update);
                }
            }
            //根据id删除信息
            else if (a == 5) {
                System.out.println("请输入学号");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("学号不正确，请重新输入");
                    String idd = sc.next();

                    Object deleteRow = dr.delete(Student.class, idd);
                    System.out.println(deleteRow);
                } else {
                    Object deleteRow = dr.delete(Student.class, id);
                    System.out.println(deleteRow);
                }
            }

        }
    }
}