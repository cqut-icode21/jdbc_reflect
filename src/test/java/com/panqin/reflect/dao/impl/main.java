package com.panqin.reflect.dao.impl;

import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;
import com.panqin.reflect.utils.DatabaseUtil;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        System.out.println("---------------------------");
        System.out.println("----------1.增加------------");
        System.out.println("----------2.删除------------");
        System.out.println("----------3.查找------------");
        System.out.println("----------4.修改------------");
        System.out.println("--------请选择你的操作--------");
        System.out.println("---------------------------");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if(input > 4 || input < 1){
            System.out.println("非法输入");
        }
        switch (input){
            case 1: {
                System.out.println("请输入用户信息");
                System.out.println("请输入id");
                int id = scanner.nextInt();
                System.out.println("请输入姓名");
                String name = scanner.next();
                System.out.println("请输入性别");
                String sex = scanner.next();
                System.out.println("请输入年龄");
                int age = scanner.nextInt();
                Teacher teacher = new Teacher(id, name, sex, age);
                databaseReflect.add(teacher);
            }

            case 2 : {
                System.out.println("请输入想要删除的人的id");
                int id = scanner.nextInt();
                databaseReflect.delete(Teacher.class, id);
            }

            case 3:{
                System.out.println("请输入想要查询对象的id");
                int id = scanner.nextInt();
                databaseReflect.findById(Teacher.class,id);

            }

            case 4:{
                System.out.println("请输入想要修改的对象的id");
                System.out.println("请输入全新的id");
                int id = scanner.nextInt();
                System.out.println("请输入全新的姓名");
                String name = scanner.next();
                System.out.println("请输入全新的性别");
                String sex = scanner.next();
                System.out.println("请输入全新的年龄");
                int age = scanner.nextInt();
                Teacher teacher = new Teacher(id,name,sex,age);
                if (databaseReflect.update(teacher,id)){
                    System.out.println("已经修改成功！");
                }else {
                    System.out.println("修改失败！");
                }
                System.out.println("\n");
            }

        }

    }


}
