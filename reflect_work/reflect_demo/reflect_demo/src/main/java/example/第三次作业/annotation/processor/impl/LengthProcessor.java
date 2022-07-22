package example.第三次作业.annotation.processor.impl;

import example.第三次作业.annotation.processor.AnnotationProcessor;
import example.第三次作业.annotation.valid.Length;
import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;

/**
 * @author LIXIN
 * @description 验证长度信息
 * @date 2022/4/5 21:16
 */
public class LengthProcessor extends AnnotationProcessor<Length> {
    @Override
    public boolean process(Object object, Field field, Length annotation) throws DataNotValidException, IllegalAccessException {
        super.process(object, field, annotation);
        //注解作用在字段上
        Object o = field.get(object);
        String s = String.valueOf(o);
        if (s.length() > annotation.max()) {
            throw new DataNotValidException(field.getName() + " 的长度为" + s.length() + "超过了最大长度 " + annotation.max());
        }
        if (s.length() < annotation.min()) {
            throw new DataNotValidException(field.getName() + " 的长度为" + s.length() + "小于了最短长度 " + annotation.min());
        }

        return true;
    }
}
