package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Name;
import com.panqin.reflect.annotation.Table;

/**
 * @author 潘琴
 * @date:2021/8/1
 * @description: 学生类
 */
@Table(tableName = "teacher", label = "学生表")
public class Teacher {
    @Id(idName = "tno")
    @Name(nameName = "tname")
    @Column(columnName = "tno", label = "教师学号")
    private String id;
    @Column(columnName = "tname", label = "教师姓名")
    private String name;
    @Column(columnName = "tage", label = "教师年龄")
    private Integer age;

    public Teacher() {
    }

    public Teacher(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
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

