package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

@Table(tableName = "teacher ", label = "老师表")
public class Teacher {
    @Id(idName = "id")
    @Column(columnName = "id", label = "学号")
    private String  id;
    @Column(columnName = "name", label = "姓名")
    private String name;
    @Column(columnName = "age", label = "年龄")
    private Integer age;
    @Column(columnName = "course", label = "教师专业")
    private String course;
    @Column(columnName = "sex", label = "性别")
    private String sex;


    public Teacher() {
    }

    public Teacher(String id, String name, Integer age,String course,String sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course=course;
        this.sex=sex;
    }

    public String getSsex() {
        return sex;
    }

    public void setSsex(String ssex) {
        this.sex = ssex;
    }

    @Override
    public String toString() {
        return "teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sdept='" + course+ '\'' +
                ", ssex='" + sex + '\'' +
                '}';
    }




    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course= course;
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
