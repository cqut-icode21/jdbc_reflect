package example.第三次作业.Entity;

public class Condition {
    String filed;
    String con;
    String value;

    public Condition(String filed, String con, String value) {
        this.filed = filed;
        this.con = con;
        this.value = value;
    }

    public static Condition of(String condition) throws Exception {
        String[] s = condition.split(" ");
        Condition condition1 = null;
        try {
            condition1 = new Condition(s[0], s[1], s[2]);
        } catch (Exception e) {
            System.out.println("格式错误");
            throw e;
        }
        return condition1;
    }

    public String getPrepared() {
        return " " + filed + " " + con + "?";
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
