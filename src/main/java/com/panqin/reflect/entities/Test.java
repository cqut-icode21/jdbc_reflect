package com.panqin.reflect.entities;


import com.panqin.reflect.dao.impl.DatabaseReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    static DatabaseReflect databaseReflect = new DatabaseReflect();

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        chooseObject();

    }

    public static void chooseObject() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        System.out.println("***************************************");
        System.out.println("**            请选择操作对象            **");
        System.out.println("**              1.学生                **");
        System.out.println("**              2.教师                **");
        System.out.println("**              3.退出                **");
        System.out.println("***************************************");
        System.out.println();
        System.out.println("请输入对应代号：");
        switch (scanner.nextInt()) {
            case 1:
                choose(Student.class);
                break;
            case 2:
                choose(Teacher.class);
                break;
            case 3:
                System.exit(0);
            default: {
                System.out.println("输入错误，请重新输入");
                chooseObject();
            }
        }
        chooseObject();
    }

    public static void choose(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("***************************************");
        System.out.println("**             请选择操作              **");
        System.out.println("**              1.添加                **");
        System.out.println("**              2.删除                **");
        System.out.println("**              3.修改                **");
        System.out.println("**              4.查询                **");
        System.out.println("**              5.返回                **");
        System.out.println("***************************************");
        System.out.println();
        System.out.println("请输入对应代号：");
        switch (scanner.nextInt()) {
            case 1:
                add(clazz);
                break;
            case 2:
                delete(clazz);
                break;
            case 3:
                upDate(clazz);
                break;
            case 4:
                find(clazz);
                break;
            case 5:
                return;
            default: {
                System.out.println("输入错误，请重新输入");
                choose(clazz);
            }
        }

    }

    public static void add(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        System.out.println("输入：id");
        String id = scanner.next();
        System.out.println("输入：name");
        String name = scanner.next();
        System.out.println("输入：age");
        int age = scanner.nextInt();
        Constructor<?> constructor = clazz.getConstructor(String.class, String.class, Integer.class);
        Object object = constructor.newInstance(id, name, age);
        if (databaseReflect.add(object)) {
            System.out.println("添加成功");
            endOrContinue();
        }
    }

    public static void delete(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("输入：id");
        String id = scanner.next();
        if (databaseReflect.delete(clazz, id)) {
            System.out.println("删除成功！");
            endOrContinue();
        }
    }

    public static void upDate(Class<?> clazz) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        System.out.println("输入：id");
        String id = scanner.next();
        Object object = databaseReflect.findById(clazz, id);
        Field[] fields = object.getClass().getDeclaredFields();
        System.out.println("依次输入修改后的值，无需修改处输-1");
        for (Field field : fields) {
            System.out.println(field.getName() + " : ");
            String input = scanner.next();
            if (!input.equals("-1")) {
                field.setAccessible(true);
                if (field.getType().getSimpleName().equals("Integer"))
                    field.set(object, Integer.valueOf(input));
                else field.set(object, input);
            }
        }
        if (databaseReflect.update(object, id)){
            System.out.println("修改成功！");
            endOrContinue();
        }

    }

    public static void find(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("输入id（输*查询全部）：");
        String input = scanner.next();
        if (input.equals("*")) {
            System.out.println(databaseReflect.findAll(clazz));
        } else {
            System.out.println(databaseReflect.findById(clazz, input));
        }
        endOrContinue();
    }

    public static void endOrContinue() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println();
        System.out.println("***************************************");
        System.out.println("**             输1返回首页             **");
        System.out.println("**               输2结束              **");
        System.out.println("***************************************");
        System.out.println();
        System.out.println("输入");
        String input = scanner.next();
        if (input.equals("1"))
            chooseObject();
        else if (input.equals("2"))
            System.exit(0);
        else{
            System.out.println("输入错误，请重新输入");
            System.out.println();
            endOrContinue();
        }
    }

}



