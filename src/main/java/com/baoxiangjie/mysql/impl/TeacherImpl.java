package com.baoxiangjie.mysql.impl;

import com.baoxiangjie.mysql.util.InputManager;

import java.lang.reflect.Field;

/**
 * 获取Teacher对象
 * 复用可能需要修改
 * @author BaoXiangjie
 */
public class TeacherImpl {
    public static <T> T getInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field currentField : fields) {
                currentField.setAccessible(true);
                String name = currentField.getName();
                Class<?> type = currentField.getType();
                Object input = null;
                if ("int".equals(type.getSimpleName())) {
                    input = InputManager.getInt(name);
                } else if ("String".equals(type.getSimpleName())) {
                    input = InputManager.getString(name);
                }
                currentField.set(instance, input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
