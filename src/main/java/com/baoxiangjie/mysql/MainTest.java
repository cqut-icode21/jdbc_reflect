package com.baoxiangjie.mysql;

import com.baoxiangjie.mysql.impl.daoImpl.BaseDaoImpl;
import com.baoxiangjie.mysql.pojo.Teacher;
import java.util.List;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BaseDaoImpl baseDao = new BaseDaoImpl();
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
                case 1 -> {
                    if (baseDao.add(Teacher.class)) {
                        System.out.println("添加成功！");
                    } else {
                        System.out.println("添加失败!");
                    }
                }
                case 2 -> {
                    System.out.print("请输入所要删除信息的id：");
                    if (baseDao.delete(Teacher.class, input.nextInt())) {
                        System.out.println("删除成功！");
                    } else {
                        System.out.println("删除失败！");
                    }
                }
                case 3 -> {
                    System.out.print("请输入所要查询信息的id：");
                    System.out.println(baseDao.findById(Teacher.class, input.nextInt()));
                }
                case 4 -> {
                    System.out.print("请输入所要修改信息的id：");
                    if (baseDao.update(Teacher.class, input.nextInt())) {
                        System.out.println("修改成功！");
                    } else {
                        System.out.println("修改失败！");
                    }
                }
                case 5 -> {
                    List<Object> list = baseDao.findAll(Teacher.class);
                    for (Object o : list) {
                        System.out.println(o);
                    }
                }
                default -> {
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
