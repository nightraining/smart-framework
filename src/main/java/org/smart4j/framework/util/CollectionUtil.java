package org.smart4j.framework.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

/**
 * 集合操作工具类
 */
public class CollectionUtil {
    /**
     * 判断集合是否非空
     * @param collection
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判断集合是否为空
     * @param collection
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }
}
