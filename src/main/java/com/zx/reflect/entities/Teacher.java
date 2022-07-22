package com.zx.reflect.entities;


import com.zx.reflect.annotation.Column;
import com.zx.reflect.annotation.Column;
import com.zx.reflect.annotation.Id;
import com.zx.reflect.annotation.Table;

@Table(tableName = "Teacher", label = "学生表")
public class Teacher {
    @Id(idName = "sid")
    @Column(columnName = "sid", label = "学号")
    private String id;
    @Column(columnName = "sname", label = "姓名")
    private String name;
    @Column(columnName = "sage", label = "年龄")
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