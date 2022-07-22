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


    @Column(columnName = "sname", label = "学生姓名")
    private String name;

    @Column(columnName = "ssex", label = "性别")
    private String sex;
    @Column(columnName = "sage", label = "学生年龄")
    private Integer age;
    @Column(columnName = "sdept" , label = "院系")
    private String dept;
    @Id(idName = "sno")
    @Column(columnName = "sno", label = "学生学号")
    private String id;

    public Student() {
    }

    public Student(String id, String name, Integer age, String sex, String dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dept = dept;
        this.sex = sex;
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

    @Override
    public String toString() {
        return "Student{ " +
                "id = '" + id + '\'' +
                ", name = '" + name + '\'' +
                ", sex = '" + sex + '\'' +
                ", dept = '" + dept + '\'' +
                ", age = " + age +
                " }";
    }
}
