import com.zx.reflect.entities.Student;

public class Text {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        System.out.println(s1.getClass()  == s2.getClass());
    }
}
