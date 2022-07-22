package src.main;

import java.util.Objects;
import java.util.Scanner;

public class MainText {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        do {
            for (int i = 0; ; i++) {
                System.out.println("------------------1. 选择学生-----------------");
                System.out.println("------------------2. 选择老师-----------------");
                System.out.println("-------------------输入类型-------------------");
                str = scanner.next();
                if (!"1".equals(str) && !"2".equals(str) && !"0".equals(str)) {
                    System.out.println("-----------输入有误，请重新输入----------");
                } else {
                    break;
                }
            }
            switch (Integer.parseInt(str)) {
                case 0:
                    System.exit(0);
                case 1:
                    StudentText studentText = new StudentText();
                    studentText.studentText();
                    break;
                case 2:
                    TeacherText teacherText = new TeacherText();
                    teacherText.teacherText();
                    break;
            }
            System.out.println("执行完毕,输入y继续");
            str = scanner.next();
        } while (Objects.equals(str, "y"));
    }
}
