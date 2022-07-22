package test;

import reflect.entities.Student;
import reflect.entities.Teacher;

import java.util.InputMismatchException;
import java.util.Scanner;

import reflect.impl.DatabaseReflect;

public class MainTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入执行的类对象名称,如：Student,Teacher");
        String str = input.next();

        if (str.equals("Student")) {
            while (true) {
                execute(Student.class);
            }
        } else if (str.equals("Teacher")) {
            while (true) {
                execute(Teacher.class);
            }
        } else {
            System.out.println("输入格式有误");
        }
    }

    public static void execute(Class<?> clazz) throws InputMismatchException {
        Scanner input = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();
        System.out.println("请输入数字执行对应功能");
        System.out.println("0:findALl  1:add  2:deleteById  3:updateById  4:findById");
        int op = input.nextInt();
        if (op >= 0 && op <= 5) {
            switch (op) {
                case 0:
                    System.out.println(databaseReflect.findAll(clazz));
                    break;
                case 1:
                    System.out.println("请输入编号");
                    int id = input.nextInt();
                    System.out.println("请输入姓名");
                    String name = input.next();
                    System.out.println("请输入性别");
                    String sex = input.next();
                    System.out.println("请输入系别");
                    String dept = input.next();
                    System.out.println("请输入年龄");
                    int age = input.nextInt();
                    databaseReflect.add(clazz, id, name, sex, dept, age);
                    break;
                case 2:
                    System.out.println("请输入对应编号进行删除");
                    int deleteId = input.nextInt();
                    System.out.println(databaseReflect.delete(clazz, deleteId));
                    System.out.println("删除成功");
                    break;
                case 3:
                    if (clazz == Student.class) {
                        System.out.println("对学生类进行修改");
                        Student student = new Student();
                        System.out.println("请输入对应编号进行更改");
                        String changeId = input.next();
                        student.setId(changeId);

                        System.out.println("修改姓名");
                        String changeName = input.next();
                        student.setName(changeName);

                        System.out.println("修改性别");
                        String changeSex = input.next();
                        student.setSex(changeSex);

                        System.out.println("修改学科");
                        String changeDept = input.next();
                        student.setDept(changeDept);

                        System.out.println("修改年龄");
                        int changeAge = input.nextInt();
                        student.setAge(changeAge);
                        System.out.println(databaseReflect.update(student, changeId));
                        System.out.println("更改成功");
                        break;
                    } else if (clazz == Teacher.class) {
                        System.out.println("对教师类进行修改");
                        Teacher teacher = new Teacher();
                        System.out.println("请输入对应编号进行更改");
                        String changeId = input.next();
                        teacher.setId(changeId);

                        System.out.println("修改姓名");
                        String changeName = input.next();
                        teacher.setName(changeName);

                        System.out.println("修改性别");
                        String changeSex = input.next();
                        teacher.setSex(changeSex);

                        System.out.println("修改学科");
                        String changeDept = input.next();
                        teacher.setDept(changeDept);

                        System.out.println("修改年龄");
                        int changeAge = input.nextInt();
                        teacher.setAge(changeAge);
                        System.out.println(databaseReflect.update(teacher, changeId));
                        System.out.println("更改成功");
                        break;
                    } else {
                        System.out.println("查无此类");
                    }
                case 4:
                    System.out.println("请输入对应编号进行查询");
                    int queryId = input.nextInt();
                    System.out.println(databaseReflect.findById(clazz, queryId));
                    System.out.println("查询成功");
                    break;
                default:
                    System.out.println("nothing");
            }
        } else {
            System.out.println("输入格式有误!");
        }
    }
}
