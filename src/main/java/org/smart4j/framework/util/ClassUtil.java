package org.smart4j.framework.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * 类操作工具类
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return ClassLoader
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取类路径
     * @return String
     */
    public static String getClassPath(){
        String classPath = "";
        URL resource = getClassLoader().getResource("");
        if (resource != null){
            classPath = resource.getPath();
        }
        return classPath;
    }

    /**
     * 加载类（将自动初始化）
     * @param className
     * @return Class<?>
     */
    public static Class<?> loadClass(String className){
        return loadClass(className,true);
    }

    /**
     * 加载类
     * @param className
     * @param isInitialized
     * @return Class<?>
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e){
            logger.error("加载类出错",e);
            throw new RuntimeException(e);
        }
        return  cls;
    }

    /**
     * 是否为 int 类型（包含 Integer 类型）
     * @param type
     * @return boolean
     */
    public static boolean isInt(Class<?> type){
        return type.equals(int.class) || type.equals(Integer.class);
    }

    /**
     * 是否为 long 类型（包括 Long 类型）
     * @param type
     * @return boolean
     */
    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    /**
     * 是否为 double 类型（包括 Double 类型）
     * @param type
     * @return boolean
     */
    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    /**
     * 是否为 String 类型
     * @param type
     * @return boolean
     */
    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }
}
