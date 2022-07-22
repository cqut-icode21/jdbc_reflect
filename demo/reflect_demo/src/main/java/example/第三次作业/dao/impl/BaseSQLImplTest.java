package example.第三次作业.dao.impl;


import example.第三次作业.Entity.Condition;
import example.第三次作业.Entity.Student;
import org.junit.Test;


public class BaseSQLImplTest {

    @Test
   public void query() throws Exception {
        CRUDIml<Student> studentCRUDIml = new CRUDIml<>();
        System.out.println(studentCRUDIml.query(Student.class, Condition.of("age < 100")));
    }

}