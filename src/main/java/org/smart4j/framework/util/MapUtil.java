package org.smart4j.framework.util;

import org.apache.commons.collections.MapUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 映射操作工具类
 */
public class MapUtil {

    /**
     * 判断 Map 是否非空
     * @param map
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * 判断 Map 是否为空
     * @param map
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 转置 Map
     * @param Map<K, V> source
     * @return Map<V, K>
     */
    public static <K, V> Map<V, K> invert(Map<K, V> source) {
        Map<V, K> target = null;
        if (isNotEmpty(source)) {
            target = new LinkedHashMap<V, K>(source.size());
            for (Map.Entry<K, V> entry : source.entrySet()) {
                target.put(entry.getValue(), entry.getKey());
            }
        }
        return target;
    }
}
