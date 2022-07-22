package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

@Table(tableName = "teacher", label = "教师表")
public class Teacher {
    @Id(idName = "id")
    @Column(columnName = "id",label = "教师职工号")
    private String id;

    @Column(columnName = "tname", label = "教师姓名")
    private String name;

    @Column(columnName = "age", label = "教师年龄")
    private Integer age;

    @Column(columnName = "gender", label = "性别")
    private String gender;

    @Column(columnName = "teach", label = "所教科目")
    private String teach;
    public Teacher(String id, String name, Integer age, String gender, String teach) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.teach = teach;
    }

    public Teacher() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
