package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

@Table(tableName = "teacher", label = "老师表")
public class Teacher {
    @Id(idName = "tno")
    @Column(columnName = "tno", label = "教师号")
    private Integer id;

    @Column(columnName = "sname", label = "教师姓名")
    private String name;

    @Column(columnName = "ssex", label = "性别")
    private String sex;

    @Column(columnName = "sage", label = "教师年龄")
    private Integer age;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
