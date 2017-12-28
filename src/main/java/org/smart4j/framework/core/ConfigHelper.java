package org.smart4j.framework.core;

import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.util.PropsUtil;

import java.util.Map;
import java.util.Properties;

/**
 * 获取属性文件中的属性值
 */
public class ConfigHelper {

    /**
     * 属性文件对象
     */
    private static final Properties configProps = PropsUtil.loadProps(FrameworkConstant.CONFIG_PROPS);

    /**
     * 获取 String 类型的属性值
     * @param key
     * @return String
     */
    public static String getString(String key){
        return PropsUtil.getString(configProps,key);
    }

    /**
     * 获取 String 类型的属性值（可指定默认值）
     * @param key
     * @param defaultValue
     * @return String
     */
    public static String getString(String key, String defaultValue){
        return PropsUtil.getString(configProps, key, defaultValue);
    }

    /**
     * 获取 int 类型的属性值
     * @param key
     * @return int
     */
    public static int getInt(String key){
        return PropsUtil.getNumber(configProps, key);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     * @param key
     * @param defaultValue
     * @return int
     */
    public static int getInt(String key, int defaultValue){
        return PropsUtil.getNumber(configProps, key, defaultValue);
    }

    /**
     * 获取 boolean 类型的属性值
     * @param key
     * @return boolean
     */
    public static boolean getBoolean(String key){
        return PropsUtil.getBoolean(configProps, key);
    }

    /**
     * 获取 boolean 类型的属性值（可指定默认值）
     * @param key
     * @param defaultValue
     * @return boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue){
        return PropsUtil.getBoolean(configProps, key, defaultValue);
    }

    /**
     * 获取指定前缀的相关属性
     * @param prefix
     * @return Map
     */
    public static Map<String,Object> getMap(String prefix){
        return PropsUtil.getMap(configProps, prefix);
    }
}
