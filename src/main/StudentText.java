package src.main;

import com.zx.reflect.dao.impl.DatabaseReflect;
import com.zx.reflect.entities.Student;

import java.util.Scanner;

public class StudentText {
    private static final Class<Student> clazz = Student.class;
    private static final DatabaseReflect databaseReflect = new DatabaseReflect();

    public void studentText() {
        String selectNumber;
        String str;
        do {
            for (int i = 0; ; i++) {
                System.out.println("----------选择你要进行的操作 -----------");
                System.out.println("---------------1. 增加---------------");
                System.out.println("---------------2. 删除---------------");
                System.out.println("---------------3. 查询---------------");
                System.out.println("---------------4. 修改---------------");
                System.out.println("---------------0. 退出---------------");
                Scanner scanner = new Scanner(System.in);
                selectNumber = scanner.next();
                if ("1".equals(selectNumber) || "2".equals(selectNumber) ||
                        "3".equals(selectNumber) || "4".equals(selectNumber) || "0".equals(selectNumber)) {
                    break;
                } else {
                    System.out.println("输入有误,请重新输入~~~");
                }
            }
            Scanner scanner = new Scanner(System.in);
            switch (Integer.parseInt(selectNumber)) {
                case 1:
                    add();
                    System.out.println("--------------------添加完成-------------------");
                    System.out.println(" ");
                    System.out.println(" ");
                    break;
                case 2:
                    delete();
                    System.out.println("--------------------删除完成-------------------");
                    System.out.println(" ");
                    System.out.println(" ");
                    break;
                case 3:
                    find();
                    System.out.println("--------------------查找完成-------------------");
                    System.out.println(" ");
                    System.out.println(" ");
                    break;
                case 4:
                    update();
                    System.out.println("--------------------修改完成-------------------");
                    System.out.println(" ");
                    System.out.println(" ");
                    break;
                case 0:
                    System.exit(0);
            }
            System.out.println("指令执行完毕,输入y继续,否则退出");
            str = scanner.next();
        } while ("y".equals(str));

    }

    public static void add() {
        Scanner input = new Scanner(System.in);
        String id, name;
        int age;
        for (int i = 0; ; i++) {
            System.out.println("输入学号: ");
            id = input.next();
            if (id.length() >= 5 && id.length() <= 8) {
                break;
            } else {
                System.out.println("id长度应为5~8,请重新输入~~~");
            }
        }
        System.out.println("输入姓名: ");
        name = input.next();
        for (int i = 0; ; i++) {
            System.out.println("输入年龄: ");
            age = input.nextInt();
            if (age >= 15 && age <= 25) {
                break;
            } else {
                System.out.println("学生年龄应为15~20岁，请重新输入~~~");
            }
        }
        Student student = new Student(id, name, age);
        databaseReflect.add(student);
    }

    public static void delete() {
        Scanner input = new Scanner(System.in);
        String id;
        for (int i = 0; ; i++) {
            System.out.println("输入要删除的人的学号: ");
            id = input.next();
            if (id.length() >= 5 && id.length() <= 8) {
                break;
            } else {
                System.out.println("id长度应为5~8,请重新输入~~~");
            }
        }
        databaseReflect.delete(clazz, id);
    }

    public static void find() {
        Scanner input = new Scanner(System.in);
        String id;
        for (int i = 0; ; i++) {
            System.out.println("输入要查询的人的学号: ");
            id = input.next();
            if (id.length() >= 5 && id.length() <= 8) {
                break;
            } else {
                System.out.println("id长度应为5~8,请重新输入~~~");
            }
        }
        Object obj = databaseReflect.findById(clazz, id);
        System.out.println(obj);
    }

    public static void update() {
        Scanner input = new Scanner(System.in);
        String id, id1, name;
        int age;
        for (int i = 0; ; i++) {
            System.out.println("输入要修改的人的学号: ");
            id = input.next();
            if (id.length() >= 5 && id.length() <= 8) {
                break;
            } else {
                System.out.println("id长度应为5~8,请重新输入~~~");
            }
        }
        System.out.println("注: 若是不愿修改的属性,可用-1或者null代替输入~~~");
        for (int i = 0; ; i++) {
            System.out.println("输入学号: ");
            id1 = input.next();
            if (id1.length() >= 5 && id1.length() <= 8 || id1.equals("-1") || id1.equals("null")) {
                break;
            } else {
                System.out.println("id长度应为5~8,请重新输入~~~");
            }
        }
        System.out.println("输入姓名: ");
        name = input.next();
        for (int i = 0; ; i++) {
            System.out.println("输入年龄: ");
            age = input.nextInt();
            if (age >= 15 && age <= 25) {
                break;
            } else {
                System.out.println("学生年龄应为15~20岁，请重新输入~~~");
            }
        }

        Student student = new Student(id1, name, age);
        databaseReflect.update(student, id);
    }
}
