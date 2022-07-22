package test;

import org.junit.jupiter.api.Test;
import reflect.entities.Student;
import reflect.entities.Teacher;
import reflect.impl.SqlDaoImpl;


public class SqlDaoImplTest {
    SqlDaoImpl sqlDao = new SqlDaoImpl();

    @Test
    public void findAllSql() {
        sqlDao.findAllSql(Teacher.class);
    }

    @Test
    public void findByIdSql() {
        sqlDao.findByIdSql(Teacher.class);

    }

    @Test
    public void deleteSql() {
        sqlDao.deleteSql(Teacher.class);

    }

    @Test
    public void addSql() {
        sqlDao.addSql(Teacher.class);

    }

    @Test
    public void updateSql() {
        sqlDao.updateSql(Teacher.class);
    }
}