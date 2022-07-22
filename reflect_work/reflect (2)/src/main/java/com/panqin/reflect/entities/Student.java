package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 学生类
 */
@Table(tableName = "Teacher", label = "教师表")
public class Student {
    @Id(idName = "tno")
    @Column(columnName = "tno", label = "教师学号")
    private int id;
    @Column(columnName = "tname", label = "教师姓名")
    private String name;
    @Column(columnName = "tage", label = "教师年龄")
    private Integer age;

    public Student(int i, String 王老师, String 女, String s) {
    }

    public Student(int id, String name, Integer age) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
