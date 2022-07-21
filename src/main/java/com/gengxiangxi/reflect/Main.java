package com.gengxiangxi.reflect;

import com.gengxiangxi.reflect.dao.impl.DatabaseReflect;
import com.gengxiangxi.reflect.entities.Teacher;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();
        System.out.println("===============================================");
        System.out.println("||                1.添加信息                   ||");
        System.out.println("||                2.删除信息                   ||");
        System.out.println("||                3.查询信息                   ||");
        System.out.println("||                4.修改信息                   ||");
        System.out.println("||                5.所有信息                   ||");
        System.out.println("===============================================");
        System.out.print("请输入序号选择功能：");
        try {
            switch (input.nextInt()) {
                case 1 : {
                    if (databaseReflect.add(Teacher.class)) {
                        System.out.println("添加成功！");
                    } else {
                        System.out.println("添加失败!");
                    }
                    break;
                }
                case 2 : {
                    System.out.print("请输入所要删除信息的id：");
                    if (databaseReflect.delete(Teacher.class, input.nextInt())) {
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("删除失败！");
                    }
                    break;
                }
                case 3 : {
                    System.out.print("请输入所要查询信息的id：");
                    System.out.println(databaseReflect.findById(Teacher.class, input.nextInt()));
                    break;
                }
                case 4 : {
                    System.out.print("请输入所要修改信息的id：");
                    if (databaseReflect.update(Teacher.class, input.nextInt())) {
                        System.out.println("修改成功！");
                    } else {
                        System.out.println("修改失败！");
                    }
                    break;
                }
                case 5 : {
                    List<?> list = databaseReflect.findAll(Teacher.class);
                    for (Object o : list) {
                        System.out.println(o);
                    }
                    break;
                }
                default : {
                    System.out.println("不是有效输入！");
                    main(null);
                }
            }
        } catch (Exception e) {
            System.out.println("输入错误！");
            main(null);
        }
        System.out.println("\n回车继续...");
        new Scanner(System.in).nextLine();
        main(null);
    }
}
