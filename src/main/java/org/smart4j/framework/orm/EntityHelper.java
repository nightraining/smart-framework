package org.smart4j.framework.orm;

import org.smart4j.framework.core.ClassHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化 Entity 结构
 */
public class EntityHelper {

    /**
     * 实体类 => 表名
     */
    private static final Map<Class<?>,String> entityClassTableNameMap = new HashMap<Class<?>, String>();

    /**
     * 实体类 => （字段名 => 列名）
     */
    private static final Map<Class<?>, Map<String, String>> entityClassFieldMapMap = new HashMap<Class<?>, Map<String, String>>();


    static {
        // 获取并遍历所有实体类
        List<Class<?>> entityClassList = ClassHelper
    }

}
