package org.smart4j.framework.ioc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化相关 Bean 类
 */
public class BeanHelper {

    /**
     * Bean Map（Bean类 => Bean 实例）
     */
    private static  final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            //获取应用包路径下所有的类
            List<Class<?>> classList = null;
        }catch (Exception e){

        }
    }
}
