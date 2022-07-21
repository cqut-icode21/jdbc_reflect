package Person;

import note.Column;
import note.Table;

@Table(tableName = "student",idLength = 11)
public class Student {
    @Column(columnName = "stu_name",type = "varchar(11)",maxLength = 11)
    private String id;
    @Column(columnName = "stu_name",type = "varchar(20)",maxLength = 20)
    private String name;
    @Column(columnName = "stu_gender",type = "varchar(1)",maxLength = 1)
    private String gender;
    @Column(columnName = "stu_major",type = "varchar(10)",maxLength = 10)
    private String major;
    @Column(columnName = "stu_class",type = "varchar(10)",maxLength = 10)
    private String sClass;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", major='" + major + '\'' +
                ", sClass='" + sClass + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }
}
