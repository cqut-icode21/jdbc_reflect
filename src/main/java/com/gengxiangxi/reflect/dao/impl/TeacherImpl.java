package com.gengxiangxi.reflect.dao.impl;

import com.gengxiangxi.reflect.utils.InputUtil;

import java.lang.reflect.Field;

//获取一个Teacher实例对象
public class TeacherImpl {
    public static <T> T getInstance(Class<T> clazz) {
        T instance = null;
        try {
            //声明Teacher实例对象
            instance = clazz.newInstance();
            //获取Teacher类所有属性
            Field[] fields = clazz.getDeclaredFields();
            //为每个属性赋值
            for (Field currentField : fields) {
                //设置当前属性可操作
                currentField.setAccessible(true);
                //获取当前属性名
                String name = currentField.getName();
                //获取当前属性类型
                Class<?> type = currentField.getType();
                //中间变量
                Object input = null;
                //判断当前属性类型，如果是int则提示输入整数，如果是String则提升输入字符串
                if ("int".equals(type.getSimpleName())) {
                    input = InputUtil.getInt(name);
                } else if ("String".equals(type.getSimpleName())) {
                    input = InputUtil.getString(name);
                }
                //设置实例化对象的当前变量的值
                currentField.set(instance, input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
