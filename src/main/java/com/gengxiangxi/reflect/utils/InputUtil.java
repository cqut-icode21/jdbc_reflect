package com.gengxiangxi.reflect.utils;

import java.util.Scanner;

public class InputUtil {
    public static Scanner scanner = new Scanner(System.in);

    public static int getInt(String def) {
        System.out.print("请输入一个整数，" + def + ":");
        int i;
        try {
            i = scanner.nextInt();
        } catch (Exception e) {
            System.out.print("输入错误！");
            scanner.next();
            i = getInt(def);
        }
        return i;
    }

    public static String getString(String def) {
        System.out.print("请输入一个字符串，" + def + ":");
        String s;
        try {
            s = scanner.next();
        } catch (Exception e) {
            System.out.print("输入错误！");
            scanner.next();
            s = getString(def);
        }
        return s;
    }
}
