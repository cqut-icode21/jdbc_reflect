package example.第三次作业.Entity;

import example.第三次作业.annotation.dao.Column;
import example.第三次作业.annotation.dao.Table;
import example.第三次作业.annotation.valid.Chinese;
import example.第三次作业.annotation.valid.Length;
import example.第三次作业.annotation.valid.Number;

@Table(value = "person")
public class Person {
    @Column
    @Chinese
    @Length(min = 2, max = 5)
    public String name;

    @Length(min = 1,max = 5)
    @Column("merried")
    public boolean isMerried;
    @Number(minValue = 0,maxValue = 120)
    @Column
    public int age;
    @Length(min = 8,max = 10)
    @Column(isId = true, value = "id_no")
    private String idNo;

    public Person() {
    }

    public Person(String idNo) {
        this.idNo = idNo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", isMerried=" + isMerried +
                ", age=" + age +
                ", idNo='" + idNo + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMerried() {
        return isMerried;
    }

    public void setMerried(boolean merried) {
        isMerried = merried;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
