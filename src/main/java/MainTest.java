import dao.DateBaseReact;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        Teacher teacher=new Teacher();
        DateBaseReact dateBaseReact=new DateBaseReact();
        Integer id= null;
        String name=null;
        String age= null;
        System.out.print("-----------");
        System.out.println("查询按1-----------");
        System.out.print("-----------");
        System.out.println("删除按2-----------");
        System.out.print("-----------");
        System.out.println("增加按3-----------");
        System.out.print("-----------");
        System.out.println("更改按4-----------");
        System.out.print("-----------");
        System.out.println("查询所有按5-----------");
        int choose=sc.nextInt();
        switch (choose) {
            case 3 -> {
                System.out.println("input id");
                id = sc.nextInt();
                System.out.println("input name");
                name = sc.next();
                System.out.println("input age");
                age = sc.next();
                teacher.setId(id);
                teacher.setAge(age);
                teacher.setName(name);
                dateBaseReact.add(teacher);
            }
            case 1 -> {
                System.out.println("input id of finding");
                id= sc.nextInt();
                System.out.println(dateBaseReact.findById(teacher.getClass(), id));
            }
            case 2 -> {
                System.out.println("input id of deleting");
                id= sc.nextInt();
                dateBaseReact.delete(teacher.getClass(),id);
            }
            case 4-> {
                System.out.println("change id");
                id = sc.nextInt();
                System.out.println("change name");
                name = sc.next();
                System.out.println("change age");
                 age = sc.next();
                teacher.setId(id);
                teacher.setAge(age);
                teacher.setName(name);
                dateBaseReact.update(teacher,id);
            }
            case 5->{
                List<Object> list=dateBaseReact.findAll(teacher.getClass());
                for(Object i :list){
                    System.out.println(i);
                }
            }
        }
     }
}
