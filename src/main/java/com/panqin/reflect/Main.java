package com.panqin.reflect;

import com.panqin.reflect.dao.impl.DatabaseReflect;
import com.panqin.reflect.entities.Student;
import com.panqin.reflect.entities.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static DatabaseReflect databaseReflect = new DatabaseReflect();
    static Class<Teacher> clazzT= Teacher.class;
    static Class<Student> clazzS= Student.class;
    static List<Teacher> listT=new ArrayList<>();
    static List<Student> listS=new ArrayList<>();
    private  static void save(int i){
        if (i==1)
            listT=databaseReflect.findAll(clazzT);
        if (i==2)
            listS=databaseReflect.findAll(clazzS);
    }

    public static void main(String[] args) {
        while(true){
            Scanner sc=new Scanner(System.in);
            System.out.println("**************************");
            System.out.println("*         选择人员         *");
            System.out.println("*         1.教师          *");
            System.out.println("*         2.学生          *");
            System.out.println("**************************");
            System.out.println("-     请输入您的操作（1-2）  -");
            int inF;
            while (true) {
                inF = sc.nextInt();
                if (judgeIn_1(inF)) break;
                else System.out.println("***请正确输入***");;
            }
            save(inF);
            System.out.println("**************************");
            System.out.println("*          选项           *");
            System.out.println("*         1.添加          *");
            System.out.println("*         2.删除          *");
            System.out.println("*         3.修改          *");
            System.out.println("*         4.查找          *");
            System.out.println("*         5.退出          *");
            System.out.println("**************************");
            System.out.println("-     请输入您的操作（1-5）  -");
            int in;
            while (true) {
                in = sc.nextInt();
                if (judgeIn_2(in)) break;
                else System.out.println("***请正确输入***");;
            }
            try {
                if (in==5)
                    break;
                exe(in,inF);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void exe(int in,int person){
        switch (in){
            case 1:
                add(person);break;
            case 2:
                delete(person);break;
            case 3:
                update(person);break;
            case 4:
                find(person);break;
        }
    }

    private static void add(int person) {
        Scanner sc = new Scanner(System.in);
        String inId, inAge, inName;
        do {
            System.out.println("-         请输入增加人员ID       -");
            inId = sc.next();
        } while (!judgeId(inId,person));
        System.out.println("-         请输入增加人员姓名       -");
        inName = sc.next();
        do {
            System.out.println("-         请输入增加人员年龄       -");
            inAge = sc.next();
        } while (!judgeAge(inAge));
        if (person==1) {
            Teacher teacher = new Teacher(inId, inName, Integer.parseInt(inAge));
            if (databaseReflect.add(teacher)) {
                System.out.println("添加成功");
                System.out.println(databaseReflect.findAll(clazzT));
            } else System.out.println("添加失败");
        }
        else {
            Student student = new Student(inId, inName, Integer.parseInt(inAge));
            if (databaseReflect.add(student)) {
                System.out.println("添加成功");
                System.out.println(databaseReflect.findAll(clazzS));
            } else System.out.println("添加失败");
        }
    }

    private static void delete(int person) {
        Scanner sc=new Scanner(System.in);
        boolean choice=true;
        while (choice) {
            System.out.println("-         请输入删除人员ID       -");
            String inID = sc.next();
            int count = 0;
            count=checkId(count,person,inID);
            if (count == 1) {
                choice=false;
                if (person==1) {
                    if (databaseReflect.delete(clazzT, inID))
                        System.out.println("删除成功");
                    else System.out.println("删除失败");
                    System.out.println(databaseReflect.findAll(clazzT));
                }
                else {
                    if (databaseReflect.delete(clazzS, inID))
                        System.out.println("删除成功");
                    else System.out.println("删除失败");
                    System.out.println(databaseReflect.findAll(clazzS));
                }
            } else {
                System.out.println("***此ID不存在***");
                System.out.println("是否继续删除？enter y or n");
                String wait=sc.next();
                if (wait.equals("n"))choice=false;
                else if (!wait.equals("y")){System.out.println("输入出错!");break;}
            }
        }
    }

    private static void update(int person) {
        Scanner sc=new Scanner(System.in);
        boolean choice=true;
        while (choice) {
            System.out.println("-         请输入修改人员ID       -");
            String inID = sc.next();
            int count = 0;
            count=checkId(count,person,inID);
            if (count == 1) {
                choice=false;
                String inAge,inName;
                System.out.println("-         请输入人员修改后姓名（若不改输入-1）       -");
                inName = sc.next();
                do {
                    System.out.println("-         请输入增加人员年龄（若不改输入-1）       -");
                    inAge = sc.next();
                    if (inAge.equals("-1"))
                        break;
                }while (!judgeAge(inAge));
                if (person==1) {
                    Teacher teacher = new Teacher(null, inName, Integer.parseInt(inAge));
                    if (databaseReflect.update(teacher, inID))
                        System.out.println("修改成功");
                    else System.out.println("修改失败");
                    System.out.println(databaseReflect.findAll(clazzT));
                }
                else {
                    Student student = new Student(null, inName, Integer.parseInt(inAge));
                    if (databaseReflect.update(student, inID))
                        System.out.println("修改成功");
                    else System.out.println("修改失败");
                    System.out.println(databaseReflect.findAll(clazzS));
                }
            } else {
                System.out.println("***此ID不存在***");
                System.out.println("是否继续修改？enter y or n");
                String wait=sc.next();
                if (wait.equals("n"))choice=false;
                else if (!wait.equals("y")){System.out.println("输入出错!");break;}
            }
        }
    }

    private static void find(int person) {
        Scanner sc=new Scanner(System.in);
        boolean choice=true;
        while (choice) {
            System.out.println("-         请输入查询人员ID       -");
            String inID = sc.next();
            int count = 0;
            count=checkId(count,person,inID);
            String out;
            if (count == 1) {
                if (person==1) {
                    out = databaseReflect.findById(clazzT, inID).toString();
                }
                else {
                    out = databaseReflect.findById(clazzS, inID).toString();
                }
                System.out.println("****************************");
                System.out.println("- "+out+" -");
                System.out.println("****************************");
                System.out.println("是否继续查询？enter y or n");
                String wait=sc.next();
                if (wait.equals("n"))choice=false;
                else if (!wait.equals("y")){System.out.println("输入出错!");break;}
            } else {
                System.out.println("***此ID不存在***");
                System.out.println("是否继续查询？enter y or n");
                String wait=sc.next();
                if (wait.equals("n"))choice=false;
                else if (!wait.equals("y")){System.out.println("输入出错!");break;}
            }
        }
    }

    private static <T>boolean judgeId(T t,int person){
        String input=String.valueOf(t);
        if (input.length()==5) {
            int n=0;
            for (int i=0;i<5;i++){
                if (!(input.charAt(i)<='9'&&input.charAt(i)>='0'))n++;
            }
            if (n==0) {
                if (person==1) {
                    for (Teacher teacher : listT) {
                        if (teacher.getId().equals(input)) {
                            System.out.println("***此ID已存在***");
                            return false;
                        }
                    }
                }
                else {
                    for (Student student : listS) {
                        if (student.getId().equals(input)) {
                            System.out.println("***此ID已存在***");
                            return false;
                        }
                    }
                }
                return true;
            }
            else {
                System.out.println("***请输入5位整数***");
                return false;
            }

        }
        else {
            System.out.println("***请输入5位整数***");
            return false;
        }
    }

    private static <T>boolean judgeAge(T t){
        Integer input= Integer.parseInt((String) t);
        if (input<200&&input>=0)
            return true;
        else {
            System.out.println("***请正确输入年龄***");
            return false;
        }
    }

    private static boolean judgeIn_1(int in){
        if (in!=1&&in!=2)
            return false;
        else return true;
    }
    private static boolean judgeIn_2(int in){
        if (!(in<=5&&in>=1))
            return false;
        else return true;
    }
    private  static int checkId(int count,int person,String inID){
        if (person==1) {
            for (Teacher teacher : listT) {
                if (teacher.getId().equals(inID)) {
                    count++;
                }
            }
        }
        else {
            for (Student student : listS) {
                if (student.getId().equals(inID)) {
                    count++;
                }
            }
        }
        return count;
    }
}

