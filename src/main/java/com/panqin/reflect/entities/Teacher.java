package com.panqin.reflect.entities;

import com.panqin.reflect.annotation.Column;
import com.panqin.reflect.annotation.Id;
import com.panqin.reflect.annotation.Table;

/**
 * @author ljq
 * @date:2021/8/1
 * @description: 教师类
 */
@Table(tableName = "teacher", label = "教师表")

public class Teacher {
    @Id(idName = "tno")
    @Column(columnName = "tno", label = "教师工号")
    private String id;

    @Column(columnName = "tname", label = "教师姓名")
    private String name;

    @Column(columnName = "tsex", label = "教师性别")
    private String sex;

    @Column(columnName = "tdept", label = "教学科目")
    private String dept;

    @Column(columnName = "tage", label = "教师年龄")
    private Integer age;

    public Teacher(){
    }

    public Teacher(String id, String name,String sex,String dept, Integer age){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.dept=dept;
        this.age=age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dept='" + dept + '\'' +
                ", age=" + age +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDept() {
        return dept;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
