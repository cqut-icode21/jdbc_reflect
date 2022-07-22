package com.panqin.reflect.dao.impl;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

public class Entrance {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

//        System.out.println();
//        System.out.println("                  *******             ********      ");
//        System.out.println("               ************         *************      ");
//        System.out.println("            *****************     *****************      ");
//        System.out.println("          ********************* *********************      ");
//        System.out.println("         *********************************************       ");
//        System.out.println("         *********************************************         ");
//        System.out.println("          *******************************************           ");
//        System.out.println("            ***************************************            ");
//        System.out.println("              ***********************************           ");
//        System.out.println("                *******************************            ");
//        System.out.println("                  ***************************           ");
//        System.out.println("                    ***********************            ");
//        System.out.println("                      *******************            ");
//        System.out.println("                        ***************            ");
//        System.out.println("                          ***********            ");
//        System.out.println("                            *******            ");
//        System.out.println("                              ***            ");
//

        test(Teacher.class);
    }

    public static <T> void test(Class<?> tClass) throws InstantiationException, IllegalAccessException {
        System.out.println("------------------------------------------------------------");
        System.out.println(" -                     输入0：查找所有数据                    - ");
        System.out.println(" -                    输入1：查找指定id数据                   - ");
        System.out.println(" -                       输入2：添加数据                     - ");
        System.out.println(" -                       输入3：删除数据                     - ");
        System.out.println(" -                       输入4：更改数据                     - ");
        System.out.println("------------------------------------------------------------");

        int i = getInt();
        switchOperation(i, tClass);
    }

    public static int getInt() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入操作：");
        int i = -1;
        try {
            i = sc.nextInt();

        } catch (Exception ex) {
            System.err.println("请输入数字");
            i = getInt();
        }
        return i;
    }

    public static <T> void switchOperation(int i, Class<?> tClass) throws InstantiationException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        DatabaseReflect databaseReflect = new DatabaseReflect();
        String id = null;
        switch (i) {
            case 0:
                List<T> list = (List<T>) databaseReflect.findAll(tClass);
                for (T obj : list) {
                    System.out.println(obj);
                }
                break;
            case 1:
                System.out.print("输入id ：");
                id = sc.next();
                System.out.println(databaseReflect.findById(tClass, id));
                break;
            case 2:
                Object t = newClass(tClass);
                databaseReflect.add(t);
                break;
            case 3:
                System.out.print("输入id ：");
                id = sc.next();
                System.out.println(databaseReflect.delete(tClass, id));
                break;
            case 4:
                System.out.print("输入你想要的更改信息的id ：");
                id = sc.next();
                Object o = databaseReflect.findById(tClass, id);
                update(o);
                databaseReflect.update(o, id);
                break;
            default:
                System.out.println("输入数字不正确");
                test(tClass);
        }
    }

    // 创建对象并设置属性值
    public static <T> T newClass(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Scanner sc = new Scanner(System.in);
        T t = clazz.newInstance();
        //获取属性值
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.label();
            System.out.println("请填写" + columnName + ": ");
            // 保证私有属性也能进行操作
            field.setAccessible(true);
            // 设置指定对象属性的值
            Class<?> type = field.getType();
            if (type.equals(Integer.class)) {
                field.set(t, sc.nextInt());
            } else if (type.equals(String.class)) {
                field.set(t, sc.next());
            }
        }
        return t;
    }

    public static void update(Object o) throws IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        int i = 0;
        //获取属性值
        Field[] fields = o.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            Field field = fields[j];

            //id 不能更改
            if (field.getAnnotation(Id.class) != null)
                continue;

            // 类中注解的字段
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName = columnAnnotation.label();
            System.out.println("是否更改" + columnName + "信息， 是输入 1 ，否输入-1: ");
            i = getInt();
            if (i == -1)
                continue;
            if (i == 1) {
                System.out.println("请输入" + columnName + "信息");
                // 保证私有属性也能进行操作
                field.setAccessible(true);
                // 设置指定对象属性的值
                Class<?> type = field.getType();
                if (type.equals(Integer.class)) {
                    field.set(o, sc.nextInt());
                } else if (type.equals(String.class)) {
                    field.set(o, sc.next());
                }
            } else {
                System.out.println("请输入1 或 -1");
                j--;
            }
        }
    }
}