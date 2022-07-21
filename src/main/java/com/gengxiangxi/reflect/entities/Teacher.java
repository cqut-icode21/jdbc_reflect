package com.gengxiangxi.reflect.entities;


import com.gengxiangxi.reflect.annotation.Column;
import com.gengxiangxi.reflect.annotation.Id;
import com.gengxiangxi.reflect.annotation.Table;

/**
 * @author gengxiangxi
 * @date:2021/8/1
 * @description: 老师类
 */
@Table(tableName = "teacher", label = "老师表")
public class Teacher {
    @Id(idName = "tid")
    @Column(columnName = "tid", label = "老师工号")
    private int id;
    @Column(columnName = "tname", label = "老师姓名")
    private String name;
    @Column(columnName = "tage", label = "老师年龄")
    private int age;

    public Teacher() {

    }

    public Teacher(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "teacher{" +
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

