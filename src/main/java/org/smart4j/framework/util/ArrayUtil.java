package org.smart4j.framework.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * 数组操作工具类
 */
public class ArrayUtil {

    /**
     * 判断数组是否非空
     * @param array
     * @return boolean
     */
    public static boolean isNotEmpty(Object[] array){
        return !ArrayUtil.isEmpty(array);
    }

    /**
     * 判断数组是否为空
     * @param array
     * @return boolean
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 连接数组
     * @param array1
     * @param array2
     * @return Object[]
     */
    public static Object[] concat(Object[] array1,Object[] array2){
        return ArrayUtils.addAll(array1,array2);
    }

    /**
     * 判断对象是否在数组中
     * @param array
     * @param obj
     * @return boolean
     */
    public static <T> boolean contains(T[] array,T obj){
        return ArrayUtils.contains(array,obj);
    }
}
