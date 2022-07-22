package test.entities;

import test.annotation.Column;
import test.annotation.Id;
import test.annotation.Table;

/**
 * @author 龙
 */
@Table(tableName = "student", label = "学生表")
public class Student {
    @Id(idName = "sid")
    @Column(columnName = "sid", label = "学生学号")
    private String id;
    @Column(columnName = "sname", label = "学生姓名")
    private String name;
    @Column(columnName = "sage", label = "学生年龄")
    private Integer age;

    public Student() {
    }

    public Student(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
