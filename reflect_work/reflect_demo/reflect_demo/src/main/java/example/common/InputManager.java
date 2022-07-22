package example.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author LIXIN
 * @description 输入
 * @date 2022/3/11 17:40
 */
public class InputManager {
    public static Scanner scanner = new Scanner(System.in);

    public static int getInt() {
        return getInt("");
    }

    public static int getInt(String def) {
        System.out.println("输入： 一个整数:" + def);
        int i = 0;
        try {
            i = scanner.nextInt();
        } catch (Exception e) {
            System.err.print("输入错误：");
            scanner.next();
            i = getInt();
        }
        return i;
    }

    public static float getFloat(String def) {
        System.out.println("输入： 一个浮点数" + def);
        float i = 0;
        try {
            i = scanner.nextFloat();
        } catch (Exception e) {
            System.err.print("输入错误：");
            scanner.next();
            i = getFloat();
        }
        return i;
    }

    public static float getFloat() {
        return getFloat("");
    }

    public static Date getDate() {
        System.out.println("输入日期：yyyy-MM-dd");
        String date = getString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
            System.out.println(parse);
        } catch (ParseException e) {
            System.err.println("错误：");
            parse = getDate();
        }
        return parse;
    }

    public static String getString(String def) {
        System.out.println("输入： 一个字符串:" + def);
        String s = scanner.nextLine();
        if (s.length() == 0) {
            s = scanner.nextLine();
        }
        if (s.length() == 0) {
            System.err.println("错误：");
            System.out.println("重新输入->");
            s = getString();
        }
        return s;
    }

    public static String getString() {
        return getString("");
    }

}
