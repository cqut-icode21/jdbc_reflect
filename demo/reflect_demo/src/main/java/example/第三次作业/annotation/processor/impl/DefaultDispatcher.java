package example.第三次作业.annotation.processor.impl;

import example.第三次作业.annotation.processor.AnnotationDispatcher;
import example.第三次作业.annotation.processor.AnnotationProcessor;
import example.第三次作业.annotation.processor.Processor;
import example.第三次作业.annotation.valid.Chinese;
import example.第三次作业.annotation.valid.Gender;
import example.第三次作业.annotation.valid.Length;
import example.第三次作业.dao.DataNotValidException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author LIXIN
 * @description 默认分发器
 * @date 2022/4/5 21:21
 */
public class DefaultDispatcher implements AnnotationDispatcher {
    AnnotationProcessor lengthProcessor, genderProcessor, numberProcessor,chineseProcessor;

    public DefaultDispatcher() {
    }

    public void volatileFiled(Object object, Field field) throws DataNotValidException, IllegalAccessException {
        lengthProcessor = new LengthProcessor();
        genderProcessor = new GenderProcessor();
        numberProcessor = new NumberProcessor();
        chineseProcessor = new ChineseProcessor();
        // fild stuId Clumn , Length
        Annotation[] declaredAnnotations = field.getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            disp(object, field, declaredAnnotation);
        }
    }

    @Override
    public void dispatcher(Object object, Field field) throws DataNotValidException, IllegalAccessException {
        lengthProcessor = new LengthProcessor();
        genderProcessor = new GenderProcessor();
        numberProcessor = new NumberProcessor();
        chineseProcessor = new ChineseProcessor();
        Class<?> clazz = object.getClass();
        //解析字段注解
        Field[] declaredFields = clazz.getDeclaredFields();
        if (field != null) {
            disp(Processor.origin, field, (Annotation) object);
            return;
        }
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            Annotation[] declaredAnnotations = declaredField.getDeclaredAnnotations();
            for (Annotation declaredAnnotation : declaredAnnotations) {
                disp(object, declaredField, declaredAnnotation);
            }
        }

    }

    private void disp(Object object, Field field, Annotation declaredAnnotation) throws DataNotValidException, IllegalAccessException {
        if (declaredAnnotation.annotationType().equals(Length.class)) {
            lengthProcessor.process(object, field, declaredAnnotation);
        } else if (declaredAnnotation.annotationType().equals(Gender.class)) {
            genderProcessor.process(object, field, declaredAnnotation);
        } else if (declaredAnnotation.annotationType().equals(Number.class)) {
            numberProcessor.process(object, field, declaredAnnotation);
        }else if (declaredAnnotation.annotationType().equals(Chinese.class)){
            chineseProcessor.process(object,field,declaredAnnotation);
        }
    }
}
