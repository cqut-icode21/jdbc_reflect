import com.Col;
import com.Table;
import  com.id;
@Table(tableName = "teacher" ,label ="老师类" )
public class Teacher {
    @id(sid = "sid")
    @Col(colName = "sid",label = "教师id")
    private Integer id;
    @Col(colName = "sname",label = "教师姓名")
    private  String name;

    @Col(colName = "sage",label = "教师年龄")
    private  String age;
    public Teacher(){

    }
    public Teacher(Integer id,String name,String age){
        this.id=id;
        this.age=age;
        this.name=name;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public Integer getId() {
        return id;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id="+this.id+"  sanme="+this.name+"  sage="+this.age;
    }

}
