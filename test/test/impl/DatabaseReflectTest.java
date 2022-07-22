package test.test.impl;

import test.dao.impl.DatabaseReflect;
import org.junit.Test;
import java.util.Scanner;


/**
 * @author 龙
 */
public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll(Object o) {
        databaseReflect.findAll(o.getClass());
    }
    @Test
    public void findById(Object o) {
        databaseReflect.findById(o.getClass() , new Object());
    }
    public void find(Object o) {
        Scanner input = new Scanner(System.in);
        System.out.println("______________________________________________________");
        System.out.println();
        System.out.println("|                    1.所有数据                       |");
        System.out.println("|                    2.单人数据                       |");
        System.out.println("______________________________________________________");
        int num = input.nextInt();
        if (num == 2) {
            findById(o);
        }
        else if (num == 1) {
            findAll(o);
        }
        else {
            System.out.println("输入错误");
            find(o);
        }
    }

    @Test
    public void delete(Object p) {
        databaseReflect.delete(p.getClass(),new Object());
    }

    @Test
    public void add(Object o) {
        databaseReflect.add(o);
    }

    @Test
    public void update(Object o) {
        databaseReflect.update(o,new Object());
    }
}