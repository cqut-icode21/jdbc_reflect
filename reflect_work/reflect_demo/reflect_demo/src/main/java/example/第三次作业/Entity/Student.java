package example.第三次作业.Entity;

import example.第三次作业.annotation.dao.Column;
import example.第三次作业.annotation.dao.Table;
import example.第三次作业.annotation.valid.Chinese;
import example.第三次作业.annotation.valid.Gender;
import example.第三次作业.annotation.valid.Length;
import example.第三次作业.annotation.valid.Number;


@Table("e_student")
public class Student {
    @Column(value = "stu_id", isId = true)
    @Length(min = 8,max = 10)
    String stuId;
    @Chinese
    @Column
    @Length(min = 2, max = 4)
    String name;

    @Column
    @Chinese
    @Gender
    String gender;
    @Column
    @Number(maxValue = 100, minValue = 16)
    int age;
    @Column
    @Length(min = 2, max = 10)
    String major;
    @Column
    @Length(min = 1, max = 4)
    int classes;
    @Column
    @Length(min = 2, max = 10)
    String college;

    public Student(String stuId, String name, String gender, int age, String major, int classes, String college) {
        this.stuId = stuId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.major = major;
        this.classes = classes;
        this.college = college;
    }

    public Student() {
    }

    public Student(String stuId) {
        this.stuId = stuId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student --->" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", classes=" + classes +
                ", college='" + college + '\'';
    }
}
