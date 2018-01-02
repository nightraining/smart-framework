package org.smart4j.framework.ioc;


import org.smart4j.framework.aop.annotation.Aspect;
import org.smart4j.framework.core.ClassHelper;
import org.smart4j.framework.core.dault.InitializationError;
import org.smart4j.framework.ioc.annotation.Bean;
import org.smart4j.framework.mvc.annotation.Action;
import org.smart4j.framework.tx.annotation.Service;

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
            List<Class<?>> classList = ClassHelper.getClassList();
            for (Class<?> cls : classList){
                //处理带有 Bean/Service/Action/Aspect 注解的类
                if (cls.isAnnotationPresent(Bean.class) ||
                    cls.isAnnotationPresent(Service.class)  ||
                    cls.isAnnotationPresent(Action.class) ||
                    cls.isAnnotationPresent(Aspect.class)) {
                    // 创建 Bean 实例
                    Object beanInstance = cls.newInstance();
                    // 将 Bean 实例放入 Bean Map 中（键为 Bean 类，值为 Bean 实例）
                    beanMap.put(cls, beanInstance);
                }
            }
        }catch (Exception e){
            throw new InitializationError("初始化 BeanHelper 出错！", e);
        }
    }

    /**
     * 获取 Bean Map
     * @return
     */
    public static  Map<Class<?>, Object> getBeanMap(){
        return beanMap;
    }

    /**
     * 获取 Bean 实例
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        if (!beanMap.containsKey(cls)){
            throw new RuntimeException("无法根据类名获取实例！"+cls);
        }
        return (T) beanMap.get(cls);
    }

    /**
     * 设置 Bean 实例
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj){
        beanMap.put(cls, obj);
    }
}
