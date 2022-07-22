package reflect.entities;

import reflect.annotation.Column;
import reflect.annotation.Id;
import reflect.annotation.Table;

/**
 * @description: 教师实体类
 */
@Table(tableName = "teacher", label = "教师表")
public class Teacher {
    @Id(idName = "tid")
    @Column(columnName = "tid", label = "教师学号")
    private String id;
    @Column(columnName = "tname", label = "教师姓名")
    private String name;
    @Column(columnName = "tsex", label = "教师性别")
    private String sex;
    @Column(columnName = "tdept", label = "所教学科")
    private String dept;
    @Column(columnName = "tage", label = "教师年龄")
    private Integer age;


    public Teacher() {
    }

    public Teacher(String id, String name, String sex, String dept, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dept = dept;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", dept=" + dept +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

