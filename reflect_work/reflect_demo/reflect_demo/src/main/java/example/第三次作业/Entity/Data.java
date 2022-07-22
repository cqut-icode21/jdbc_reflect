package example.第三次作业.Entity;

import example.common.InputManager;
import example.第三次作业.annotation.processor.Processor;
import example.第三次作业.annotation.processor.impl.DefaultDispatcher;
import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;
import java.util.Objects;

public class Data {
    //忽略关键字
    private static final String INGORE = "-1";

    public static <T> T getInstance(Class<T> clazz, boolean checkValidity) {
        //clazz= Student.class ,checkValidity=true
        T t = null;
        //Student t =null;
        try {
            t = clazz.newInstance();//new Student();
            //拿到所有声明过的字段
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                //i=0  declaredField=>stuId 字段
                Field declaredField = declaredFields[i];
                //设置可访问
                declaredField.setAccessible(true);
                //获取字段的名字stuId
                String name = declaredField.getName();
                //获取字段的类型 Sting.class
                Class<?> type = declaredField.getType();
                //输入 值
                Object input = null;
                if ("int".equals(type.getSimpleName())) {
                    input = InputManager.getInt(name);
                } else if (type.equals(String.class)) {
                    input = InputManager.getString(name);
                } else if ("boolean".equals(type.getName())) {
                    input = InputManager.getString(name);
                }
//                input=1234323423 IGNORE 跳过当前字段赋值
                if (checkValidity && !Objects.equals(String.valueOf(input), INGORE)) {
                    if ("boolean".equals(type.getName())) {//如果要求输入布尔类型的字段， “true”
                        input = Boolean.parseBoolean(String.valueOf(input));
                    }
                    //stuId t：student
                    declaredField.set(t, input);
                    try {
//                        给注解处理器指定要处理的对象 t=student
                        Processor.origin = t;
                        new DefaultDispatcher().volatileFiled(t, declaredField);
                    } catch (DataNotValidException e) {
                        Object r = null;
                        if ("int".equals(type.getSimpleName())) {
                            r = 0;
                        } else if (type.equals(String.class)) {
                            r = "";
                        }
                        System.err.println(e.getMessage());
                        declaredField.set(t, r);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        i--;
                    }
                }
                if (!Objects.equals(input, INGORE)) {
                    declaredField.set(t, input);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
