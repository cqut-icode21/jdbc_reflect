package com.baoxiangjie.mysql.util;

import java.util.Scanner;

public class GetUpdateCondition {

    public static String getCondition() {
        Scanner input = new Scanner(System.in);
        StringBuilder condition = new StringBuilder();
        String[] stringsColumn;
        String[] stringsValue;
        try {
            System.out.print("请输入想改的字段（每个字段之间用逗号隔开）：");
            stringsColumn = input.next().split("[,，]");
            System.out.print("请输入改后的字段值（每个值之间用逗号隔开，请在汉字段开头和末尾加上西文单引号）：");
            stringsValue = input.next().split("[,，]");
            for (int i = 0; i < stringsColumn.length; i++) {
                condition.append(stringsColumn[i]).append("=");
                condition.append(stringsValue[i]).append(",");
            }
            condition.deleteCharAt(condition.length() - 1);
        } catch (Exception e) {
            System.out.print("输入错误！");
            input.next();
            condition.append(getCondition());
        }
        return condition.toString();
    }

}
