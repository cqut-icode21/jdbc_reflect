package example.第三次作业.annotation.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LIXIN
 * @description 描述数据库列名
 * @date 2022/4/5 20:47
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value() default "";
    boolean nullable() default false;
    boolean isId() default false;
}
