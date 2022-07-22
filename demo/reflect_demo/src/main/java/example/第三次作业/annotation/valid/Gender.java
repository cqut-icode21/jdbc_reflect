package example.第三次作业.annotation.valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LIXIN
 * @description 描述性别
 * @date 2022/4/5 20:55
 */
@Length(max = 1,min = 1)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Gender {
}



