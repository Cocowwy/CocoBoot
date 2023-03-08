package cn.cocowwy.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 【工具】注解
 * @author <a href="https://github.com/Cocowwy">Cocowwy</a>
 * @since 2023/3/7
 */
public class AnnotatedUtils {

    /**
     * 获取指定字段的指定的注解
     * @param field 字段
     * @param annotationClass 注解的clz
     * @return 指定字段的指定注解
     */
    public static <T extends Annotation> T getAnnotated(Field field, Class<T> annotationClass) {
        return field.getAnnotation(annotationClass);
    }

    /**
     * 判断字段是否标记了指定的注解
     * @param field 字段
     * @param annotationClass 注解的clz
     * @return
     */
    public static boolean isAnnotatedWith(Field field, Class<? extends Annotation> annotationClass) {
        return field != null && getAnnotated(field, annotationClass) != null;
    }

    /**
     * 获取指定字段的指定的注解
     * @param object 类
     * @param annotationClass 注解的clz
     * @return 返回指定的注解
     */
    public static <T extends Annotation> T getAnnotated(Object object, Class<T> annotationClass) {
        return object.getClass().getAnnotation(annotationClass);
    }

    /**
     * 判断类是否标记了指定注解
     * @param object 类
     * @return 是否标记了指定的注解
     */
    public static boolean isAnnotatedWith(Object object, Class<? extends Annotation> annotationClass) {
        return object != null && getAnnotated(object, annotationClass) != null;
    }

    /**
     * 获取注解的字段值
     * @param object 类
     * @param containsSuper 是否获取父类
     * @return 字段集合
     */
    public static List<Field> getAllFields(Object object, Boolean containsSuper) {
        if (object == null) {
            return Collections.emptyList();
        }
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();

        if (containsSuper) {
            while (null != clazz) {
                fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
                clazz = clazz.getSuperclass();
            }
        } else {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
        }

        return fieldList;
    }
}
