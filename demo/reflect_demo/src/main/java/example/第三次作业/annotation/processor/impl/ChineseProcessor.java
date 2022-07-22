package example.第三次作业.annotation.processor.impl;

import example.第三次作业.annotation.processor.AnnotationProcessor;
import example.第三次作业.annotation.valid.Chinese;
import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;

public class ChineseProcessor extends AnnotationProcessor<Chinese> {
    @Override
    public boolean process(Object object, Field field, Chinese annotation) throws DataNotValidException, IllegalAccessException {
        super.process(object, field, annotation);
        Object o = field.get(object);
        String s = String.valueOf(o);
        System.out.println(s);
        boolean matches = s.matches("^[\u4E00-\u9FA5]{1,100}$");
        if (!matches) {
            throw new DataNotValidException("字段必需为汉字");
        }
        return true;
    }
}
