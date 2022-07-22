package example.第三次作业.annotation.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *@author LIXIN
 *@description 描述一个字段大小信息
 *@date 2022/4/5 20:53
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Number {
    int maxValue() default Integer.MAX_VALUE;
    int minValue() default Integer.MIN_VALUE;
}
