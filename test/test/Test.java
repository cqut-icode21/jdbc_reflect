package test.test;

import test.entities.Student;
import test.entities.Teacher;
import test.test.impl.DatabaseReflectTest;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author ZI
 */
public class Test {
    boolean bo = true;
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        new Test().begin();
    }

    public void student() {
        while (bo) {
            System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
            System.out.println("卍                    1.增加数据                       卐");
            System.out.println("卍                    2.删除数据                       卐");
            System.out.println("卍                    3.修改数据                       卐");
            System.out.println("卍                    4.查询数据                       卐");
            System.out.println("卍                    5.终止操作                       卐");
            System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
            System.out.println("请输入你选择的功能");
            int num1 = input.nextInt();
            if (num1 > 5 || num1 < 1) {
                System.out.println("输入有误");
                continue;
            }
            switch (num1) {
                case 1:new DatabaseReflectTest().add(new Student());set();break;
                case 2:new DatabaseReflectTest().delete(new Student());set();break;
                case 3:new DatabaseReflectTest().update(new Student());set();break;
                case 4:System.out.println("请选择查询所有还是单人");new DatabaseReflectTest().find(new Student());set();break;
                case 5:bo = false;break;
                default:break;
            }
        }
    }

    public void teacher() {
        boolean bo = true;
        while (bo) {
            System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
            System.out.println("卍                    1.增加数据                       卐");
            System.out.println("卍                    2.删除数据                       卐");
            System.out.println("卍                    3.修改数据                       卐");
            System.out.println("卍                    4.查询数据                       卐");
            System.out.println("卍                    5.终止操作                       卐");
            System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
            System.out.println("请输入你选择的功能");
            int num = input.nextInt();
            if (num > 5 || num < 1) {
                System.out.println("输入有误");
                continue;
            }
            switch (num) {
                case 1:new DatabaseReflectTest().add(new Teacher());set();break;
                case 2:new DatabaseReflectTest().delete(new Teacher());set();break;
                case 3:new DatabaseReflectTest().update(new Teacher());set();break;
                case 4:System.out.println("请选择查询所有还是单人");new DatabaseReflectTest().find(new Teacher());set();break;
                case 5:bo = false;break;
                default:break;
            }
        }
    }

    public void begin() {
        bo = true;
        System.out.println("请选择操作教师表还是学生表");
        System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
        System.out.println("卍                     1.学生表                       卐");
        System.out.println("卍                     2.教师表                       卐");
        System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
        String num = input.nextLine();
        if ("1".equals(num)) {
            new Test().student();
        } else if (Objects.equals(num, "2")) {
            new Test().teacher();
        } else {
            System.out.println("输入错误");
            begin();
        }
    }

    public void set() {
        System.out.println("是否继续操作该表");
        System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
        System.out.println("卍                        1.是                       卐");
        System.out.println("卍                        2.否                       卐");
        System.out.println("☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀☀");
        String a = input.next();
        if ("1".equals(a)) {
            System.out.println("继续操作");
        } else if (Objects.equals(a, "2")) {
            bo = false;
            begin();
        }else {
            System.out.println("输入错误");
            set();
        }
    }
}