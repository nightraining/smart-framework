package org.smart4j.framework.core;

import org.smart4j.framework.InstanceFactory;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 根据条件获取相关类
 */
public class ClassHelper {

    /**
     * 获取基础包名
     */
    private static final String basePackage = ConfigHelper.getString("smart.framework.app.base_package");

    /**
     * 获取ClassScanner
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * 获取基础包名下的所有类
     * @return List<Class<?>>
     */
    public static List<Class<?>> getClassList(){
        return classScanner.getClassList(basePackage);
    }

    /**
     * 获取基础包名中指定的父类或接口的相关类
     * @param superClass
     * @return List<Class<?>>
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass){
        return classScanner.getClassListBySuper(basePackage,superClass);
    }

    /**
     * 获取基础包名中指定注解的相关类
     * @param annotationClass
     * @return List<Class<?>>
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass){
        return classScanner.getClassListByAnnotation(basePackage, annotationClass);
    }

}
