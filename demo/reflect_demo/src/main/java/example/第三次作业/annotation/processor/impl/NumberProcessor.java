package example.第三次作业.annotation.processor.impl;

import example.第三次作业.annotation.processor.AnnotationProcessor;
import example.第三次作业.annotation.valid.Number;
import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;

/**
 * @author LIXIN
 * @description 验证长度信息
 * @date 2022/4/5 21:16
 */
public class NumberProcessor extends AnnotationProcessor<Number> {
    @Override
    public boolean process(Object object, Field field, Number annotation) throws DataNotValidException, IllegalAccessException {
        super.process(object, field, annotation);
        Object o = field.get(object);
        int s;
        if (o instanceof String) {
            s = Integer.parseInt((String) o);
        } else {
            s = (int) o;
        }
        if (s > annotation.maxValue()) {
            throw new DataNotValidException("字段：" + field.getName() + " 的值：" + s + "大于最大值" + annotation.maxValue());
        }
        if (s < annotation.minValue()) {
            throw new DataNotValidException("字段：" + field.getName() + " 的值：" + s + "小于最小值" + annotation.minValue());
        }
        return true;
    }
}
