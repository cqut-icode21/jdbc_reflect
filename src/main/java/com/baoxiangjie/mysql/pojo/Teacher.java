package com.baoxiangjie.mysql.pojo;

import com.baoxiangjie.mysql.annotation.Column;
import com.baoxiangjie.mysql.annotation.Id;
import com.baoxiangjie.mysql.annotation.Table;

/**
 * Teacher实体类
 * @author BaoXiangjie
 */
@Table(table_name = "all_teachers")
public class Teacher {
    @Id(value = "tid")
    @Column(value = "tid")
    private int id;
    @Column(value = "tname")
    private String name;
    @Column(value = "tsex")
    private String sex;
    @Column(value = "tage")
    private int age;
    @Column(value = "tclass")
    private String object;

    public Teacher() {
    }

    public Teacher(int id, String name, String sex, int age, String object) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.object = object;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age + '\'' +
                ", object='" + object + '\'' +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
