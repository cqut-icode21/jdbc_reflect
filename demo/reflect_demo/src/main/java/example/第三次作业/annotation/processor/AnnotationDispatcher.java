package example.第三次作业.annotation.processor;

import example.第三次作业.dao.DataNotValidException;

import java.lang.reflect.Field;

/**
 *@author LIXIN
 *@description 注解分发器，吧注解分配给指定处理器
 *@date 2022/4/5 21:19
 */
public interface AnnotationDispatcher {
   /**
    * @param object 解析的对象
    * @param field 注解的字段
    * @throws DataNotValidException
    * @throws IllegalAccessException
    */
   void dispatcher(Object object, Field field) throws DataNotValidException,IllegalAccessException;
}
