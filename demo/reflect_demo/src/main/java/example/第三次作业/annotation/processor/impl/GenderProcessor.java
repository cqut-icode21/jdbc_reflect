package example.第三次作业.annotation.processor.impl;

import example.第三次作业.annotation.processor.AnnotationProcessor;
import example.第三次作业.annotation.valid.Gender;
import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;

/**
 * @author LIXIN
 * @description 验证长度信息
 * @date 2022/4/5 21:16
 */
public class GenderProcessor extends AnnotationProcessor<Gender> {
    @Override
    public boolean process(Object object, Field field, Gender annotation) throws DataNotValidException, IllegalAccessException {
        super.process(object, field, annotation);
        return true;
    }
}
