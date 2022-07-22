package com.panqin.reflect.dao.impl;
import com.panqin.reflect.entities.Student;
import jdk.nashorn.internal.ir.WhileNode;
import org.junit.Test;

import java.util.Scanner;//先导包
import com.panqin.reflect.dao.impl.DatabaseReflect;
public class sysytem {
    public static void main(String[] args) {

        while(true)
       {
           System.out.println("=========================================");
           System.out.println("===============学生数据操作系统=============");
           System.out.println("=             1,添加数据记录              =");
           System.out.println("=             2,查找单个数据记录           =");
           System.out.println("=             3,修改数据记录              =");
           System.out.println("=             4,删除数据记录              =");
           System.out.println("=             5,查找所有数据记录           =");
           System.out.println("==========================================");
           System.out.println("=============输入一个整数选择操作=============");
            Scanner sc = new Scanner(System.in);
            int a1 = sc.nextInt();//获取的输入为int类型
            method(a1);
        }

    }

    public static void method(int number) {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        if (number == 1) {
            System.out.println("添加操作" );
            System.out.println("要创建对象，输入id(string),name(string),age(int),sdept(string),sex(string)");
            System.out.println("每次输入的参数要空一格");

            Scanner sc = new Scanner(System.in);
            String S1 = sc.next();
            String S2 = sc.next();
            Integer N1=(Integer)sc.nextInt();
            String S4 = sc.next();
            String S5 = sc.next();
            Student student=makeStudent(S1,S2,N1,S4,S5);
            databaseReflect.add(student);



        }

        if (number == 2) {
            System.out.println("查找操作" );
            System.out.println("请输入查找的id值");
            Scanner sc = new Scanner(System.in);
            String S1 = sc.next();
            Student st=new Student();
            System.out.println(databaseReflect.findById(st.getClass(), S1));

        }
        if (number == 3) {
            System.out.println("修改操作" );
            System.out.println("要创建新的对象替代原有对象，输入id(string),name(string),age(int),sdept(string),sex(string),替换目标对象的id(string)");
            System.out.println("每次输入的参数要空一格");

            Scanner sc = new Scanner(System.in);
            String S1 = sc.next();
            String S2 = sc.next();
            Integer N1=(Integer)sc.nextInt();
            String S4 = sc.next();
            String S5 = sc.next();
//            String S6 = sc.next();
            Student student=makeStudent(S1,S2,N1,S4,S5);
            System.out.println(databaseReflect.update(student,S1));
        }
        if (number == 4) {
            System.out.println("删除操作" );
            System.out.println("输入id值");
            Scanner sc = new Scanner(System.in);
            String S1 = sc.next();
            System.out.println(databaseReflect.delete(Student.class, S1));
        }

        if (number==5){
            System.out.println("遍历数据库操作");
            System.out.println(databaseReflect.findAll(Student.class));
        }

    }

    public static Student makeStudent(String s1,String s2,Integer n1,String s4,String s5) {
        Student student=new Student(s1,s2,n1,s4,s5);
        return student;
    }

}



