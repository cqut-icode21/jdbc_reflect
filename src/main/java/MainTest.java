import Dao.dao;
import Person.Student;
import note.Column;
import note.Table;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class MainTest {
    static int end;
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        test(Student.class);
    }
    public static <T> void test(Class<T> clazz){
        end = 1;
        while(end==1){
            System.out.println("__________________欢迎来到人员管理系统______________________");
            System.out.println("|                   1.添加人员                            |");
            System.out.println("|                   2.删除人员                            |");
            System.out.println("|                   3.修改人员                            |");
            System.out.println("|                   4.查询人员                            |");
            System.out.println("|                   5.查看全体人员                         |");
            System.out.println("|                   6.退出系统                            |");
            System.out.println("|_________________-----------------______________________|");
            System.out.println("请输入你的操作：");
            int flag = 1;
            int command = 0;
            while(flag==1){
                try{
                    command = sc.nextInt();
                    choose(command,clazz);
                    flag = 0;
                }catch (InputMismatchException e){
                    System.out.println("输入非法指令!请重新输入指令");
                    sc.nextLine();
                } catch (InstantiationException | IllegalAccessException | SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("-------欢迎下次使用-------");
    }
    public static <T> void choose(int command,Class<T> clazz) throws NumberFormatException, InstantiationException, IllegalAccessException, SQLException {
        Table table = clazz.getAnnotation(Table.class);
        switch (command){
            case 1:
                addPerson(clazz,table);
                break;
            case 2:
                deletePerson(table);
                break;
            case 3:
                System.out.println("请你输入要修改人员的id:");
                String id = sc.next();
                while(id.length()!= table.idLength()){
                    System.out.println("输入有误！请输入"+table.idLength()+"位的id!");
                    id = sc.next();
                }
                dao.deleteOne(id,table);
                changePerson(clazz,table);
                break;
            case 4:
                selectPerson(table);
                break;
            case 5:
                dao.selectAll(table);
                break;
            case 6:
                end = 0;
                break;
            default:
                throw new InputMismatchException();
        }
    }
    public static <T> void addPerson(Class<T> clazz,Table table) throws InstantiationException, IllegalAccessException {

        Field[] fields = clazz.getDeclaredFields();
        T object = clazz.newInstance();

        for(Field f : fields){
            f.setAccessible(true);
            Column ano = f.getAnnotation(Column.class);


            String s = f.getName();
            System.out.println("请输入"+s+":"+ano.type());
            String temp = sc.next();
            if(s.equals("gender")){
                while(!(temp.equals("男")||temp.equals("女"))){
                    System.out.println("输入有误！请重新输入");
                    temp = sc.next();
                }
            }else if(s.equals("id")||s.equals("number")){
                while(temp.matches("\\d")||temp.length()!=ano.maxLength()){
                    if(s.equals("id")&&ano.maxLength()==11){
                        System.out.println("请输入11位学号");
                    }else if(ano.maxLength()==7 &&s.equals("id")){
                        System.out.println("请输入7位工作号");
                    }else{
                        System.out.println("请输入11位手机号");
                    }
                    temp = sc.next();
                }
            }else{
                while(temp.length()>ano.maxLength()){
                    System.out.println("超过最大长度"+ano.maxLength());
                    temp = sc.next();
                }
            }

            f.set(object,temp);
        }
        try{
            dao.addOne(object,table);
        }catch (SQLException e){
            System.out.println("数据库中已有输入的id或number,请重新输入");
            addPerson(clazz,table);
        }
        System.out.println("添加成功");
    }
    public static <T> void deletePerson(Table table) throws SQLException {
        String tableName = table.tableName();

        System.out.println("请输入要删除的人员的ID:");
        String id = sc.next();
        while(id.length()!= table.idLength()){
            System.out.println("请输入"+table.idLength()+"位的id!");
            id = sc.next();
        }
        dao.deleteOne(id,table);
        System.out.println("删除成功！");
    }

    public static <T> void selectPerson(Table table) {
        System.out.println("请输入要查询人员的id:");
        String id = sc.next();
        while(id.length()!=table.idLength()){
            System.out.println("请输入"+table.idLength()+"位的id!");
            id = sc.next();
        }

        try {
            dao.selectOne(id,table);
            System.out.println("完成查询");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> void changePerson(Class<T> clazz,Table table) throws InstantiationException, IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        T object = clazz.newInstance();

        for(Field f : fields){
            f.setAccessible(true);
            Column ano = f.getAnnotation(Column.class);


            String s = f.getName();
            System.out.println("请输入修改后的"+s+":"+ano.type());
            String temp = sc.next();
            if(s.equals("gender")){
                while(!(temp.equals("男")||temp.equals("女"))){
                    System.out.println("输入有误！请重新输入");
                    temp = sc.next();
                }
            }else if(s.equals("id")||s.equals("number")){
                while(temp.matches("\\d")||temp.length()!=ano.maxLength()){
                    if(s.equals("id")&&ano.maxLength()==11){
                        System.out.println("请输入11位学号");
                    }else if(ano.maxLength()==7 &&s.equals("id")){
                        System.out.println("请输入7位工作号");
                    }else{
                        System.out.println("请输入11位手机号");
                    }
                    temp = sc.next();
                }
            }else{
                while(temp.length()>ano.maxLength()){
                    System.out.println("超过最大长度"+ano.maxLength());
                    temp = sc.next();
                }
            }

            f.set(object,temp);
        }
        try{
            dao.addOne(object,table);
        }catch (SQLException e){
            System.out.println("数据库中已有输入的id或number,请重新输入！");
            addPerson(clazz,table);
        }
    }
}
