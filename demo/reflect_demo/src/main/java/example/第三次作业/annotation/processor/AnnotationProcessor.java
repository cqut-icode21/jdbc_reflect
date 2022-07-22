package example.第三次作业.annotation.processor;

import example.第三次作业.annotation.processor.impl.DefaultDispatcher;
import example.第三次作业.dao.DataNotValidException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author LIXIN
 * @description 注解处理器
 * @date 2022/4/5 21:12
 */
public abstract class AnnotationProcessor<T extends Annotation> {
    AnnotationDispatcher annotationDispatcher = new DefaultDispatcher();

    /**
     * @param object     目标对象
     * @param field      字段
     * @param annotation 目标注解
     * @return 处理结果
     * @throws DataNotValidException,IllegalArgumentException 数据验证出错
     */
    public boolean process(Object object, Field field, T annotation) throws DataNotValidException, IllegalAccessException {
        System.out.println("执行注解处理器：" + this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".") + 1) + "字段：" + field.getName());
        //递归处理注解上的注解，拿到注解上的注解
        Class<? extends Annotation> annotationType = annotation.annotationType();
        Annotation[] annotations = annotationType.getAnnotations();
        for (Annotation annotation1 : annotations) {
            //过滤掉元注解
            if (!annotation1.annotationType().toString().contains("java.lang.annotation")) {
                System.out.println("递归注解："+annotation1);
                annotationDispatcher.dispatcher(annotation1, field);
            }
        }
        return true;
    }

}
