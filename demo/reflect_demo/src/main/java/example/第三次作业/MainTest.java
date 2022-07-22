package example.第三次作业;

import example.common.InputManager;
import example.第三次作业.Entity.Condition;
import example.第三次作业.Entity.Data;
import example.第三次作业.Entity.Student;
import example.第三次作业.dao.impl.CRUDIml;
import example.第三次作业.proxy.DaoFactory;

import java.util.List;

/**
 * @author LIXIN
 */
public class MainTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        test(Student.class);
    }

    public static <T> void printInfo(List<T> tList) {
        System.out.println("----------------------------------------------------");
        if (tList.size() == 0) {
            System.err.println("没有查询到数据");
        } else {
            tList.forEach(System.err::println);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");
    }

    public static <T> void exec(CRUDIml<T> crudIml, Class<T> tClass, int op) throws Exception {
        //op =1 tClass=Student.class, crudIml =new CRUDIml<T>()
        switch (op) {
            case 1:
                //T Student
                T instance = Data.getInsta1nce(tClass, true);
                System.out.println(instance);
                crudIml.saveOne(instance);
                System.out.println("保存成功");
                break;
            case 2:
                crudIml.deleteOne(tClass, Condition.of(InputManager.getString("输入删除条件where column=? op=? value=?")));
                System.out.println("删除成功");
                break;
            case 3:
                T stu = Data.getInstance(tClass, true);
                boolean b = crudIml.updateOne(stu, Condition.of(InputManager.getString("输入更新条件 where column=? op=? value=?")));
                if (b) {
                    System.out.println("更新成功");
                }
                break;
            case 4:
                List<T> student = crudIml.query(tClass, Condition.of(InputManager.getString("输入查询条件where column=? op=? value=?")));
                printInfo(student);
                break;
            default:
                System.out.println("nothing");
        }
    }
    //T student ，clzz Student.class
    public static <T> void test(Class<T> clazz) {
                        //        new CRUDIml<>();
        CRUDIml<T> crudIml = DaoFactory.getInstance(clazz);
        while (true) {
            System.out.println("_________________-----------------______________________");
            System.out.println("|                   1.保存数据                            |");
            System.out.println("|                   2.删除数据                            |");
            System.out.println("|                   3.修改数据                            |");
            System.out.println("|                   4.查询数据                            |");
            System.out.println("|_________________-----------------______________________|");
            int op = InputManager.getInt("选择操作");
            try {
                exec(crudIml, clazz, op);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
