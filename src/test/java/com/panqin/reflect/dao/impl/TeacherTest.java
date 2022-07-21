package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;

import java.util.Scanner;

public class TeacherTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DatabaseReflect dr = new DatabaseReflect();
        Teacher teacher = new Teacher();

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
                Object findAll = dr.findAll(Teacher.class);
                System.out.println(findAll);
            }
            //根据id查询
            else if (a == 2) {
                System.out.println("请输入工号");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("工号不正确，请重新输入");
                    String idd = sc.next();
                    Object findId = dr.findById(Teacher.class, idd);
                    System.out.println(findId);

                } else {
                    Object findId = dr.findById(Teacher.class, id);
                    System.out.println(findId);
                }
            }
            //添加信息
            else if (a == 3) {
                System.out.println("请输入添加教师信息");
                System.out.println("请输入教师工号：");
                String id = sc.next();

                if (id.length() != 11) {
                    System.out.println("工号不正确，请重新输入");
                    String idd = sc.next();
                    System.out.println("请输入教师姓名：");
                    String name = sc.next();
                    System.out.println("请输入教师年龄：");
                    Integer age = sc.nextInt();
                    teacher = new Teacher(idd, name, age);

                    Object add = dr.add(teacher);
                    System.out.println(teacher);
                    System.out.println(add);
                } else {
                    System.out.println("请输入教师姓名：");
                    String name = sc.next();
                    System.out.println("请输入教师年龄：");
                    Integer age = sc.nextInt();
                    teacher = new Teacher(id, name, age);

                    Object add = dr.add(teacher);
                    System.out.println(teacher);
                    System.out.println(add);
                }
            }
            //修改信息
            else if (a == 4) {
                System.out.println("请输入以下信息");
                System.out.println("请输入教师工号：");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("工号不正确，请重新输入");
                    String idd = sc.next();
                    System.out.println("请输入教师姓名：");
                    String name = sc.next();
                    System.out.println("请输入教师年龄：");
                    Integer age = sc.nextInt();
                    teacher = new Teacher(id, name, age);

                    Object update = dr.update(teacher, idd);
                    System.out.println(update);

                } else {
                    System.out.println("请输入教师姓名：");
                    String name = sc.next();
                    System.out.println("请输入教师年龄：");
                    Integer age = sc.nextInt();
                    teacher = new Teacher(id, name, age);

                    Object update = dr.update(teacher, id);
                    System.out.println(update);
                }
            }
            //根据id删除信息
            else if (a == 5) {
                System.out.println("请输入工号");
                String id = sc.next();
                if (id.length() != 11) {
                    System.out.println("工号不正确，请重新输入");
                    String idd = sc.next();

                    Object deleteRow = dr.delete(Teacher.class, idd);
                    System.out.println(deleteRow);
                } else {
                    Object deleteRow = dr.delete(Teacher.class, id);
                    System.out.println(deleteRow);
                }
            }


        }
    }
}