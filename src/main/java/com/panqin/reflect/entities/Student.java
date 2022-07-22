package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 学生类
 */
@Table(tableName = "student", label = "学生表")
public class Student {
    @Id(idName = "sno")
    @Column(columnName = "sno", label = "学生学号")
    private String id;
    @Column(columnName = "sname", label = "学生姓名")
    private String name;
    @Column(columnName = "ssex", label = "学生性别")
    private String sex;
    @Column(columnName = "sdept", label = "学生专业")
    private String dept;
    @Column(columnName = "sage", label = "学生年龄")
    private Integer age;

    public Student() {
    }

    public Student(String id, String name,String sex,String dept, Integer age) {
        this.id = id;
        this.name = name;
        this.sex=sex;
        this.dept=dept;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex="+sex+
                ", dept="+dept+
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

    public void setSex(String sex){this.sex=sex;}

    public String getSex(){return sex;}

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
