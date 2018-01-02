package org.smart4j.framework;

import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.core.impl.DefaultClassScanner;
import org.smart4j.framework.dao.DataAccessor;
import org.smart4j.framework.dao.impl.DefaultDataAccessor;
import org.smart4j.framework.ds.DataSourceFactory;
import org.smart4j.framework.ds.impl.DefaultDataSourceFactory;
import org.smart4j.framework.mvc.HandlerExceptionResolver;
import org.smart4j.framework.mvc.HandlerInvoker;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.impl.DefaultHandlerExceptionResolver;
import org.smart4j.framework.mvc.impl.DefaultHandlerInvoker;
import org.smart4j.framework.mvc.impl.DefaultHandlerMapping;
import org.smart4j.framework.mvc.impl.DefaultViewResolver;
import org.smart4j.framework.util.ObjectUtil;
import org.smart4j.framework.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实例工厂
 */
public class InstanceFactory {

    /**
     * 用于缓存对象的实例
     */
    private static final Map<String,Object> cache = new ConcurrentHashMap<String, Object>();

    /**
     * ClassScanner
     */
    private static final String CLASS_SCANNER = "smart.framework.custom.class_scanner";

    /**
     * DataSourceFactory
     */
    private static final String DS_FACTORY = "smart.framework.custom.ds_factory";

    /**
     * DataAccessor
     */
    private static final String DATA_ACCESSOR = "smart.framework.custom.data_accessor";

    /**
     * HandlerMapping
     */
    private static final String HANDLER_MAPPING = "smart.framework.custom.handler_mapping";

    /**
     * HandlerInvoker
     */
    private static final String HANDLER_INVOKER = "smart.framework.custom.handler_invoker";

    /**
     * HandlerExceptionResolver
     */
    public static final String HNADLER_EXCEPTION_RESOLVER = "smart.framework.custom.handler_exception_resolver";

    /**
     * ViewResolver
     */
    public static final String VIEW_RRSOLVER = "smart.framework.custom.view_resolver";

    /**
     * 获取 ClassScanner
     * @return ClassScanner
     */
    public static ClassScanner getClassScanner(){
        return getInstance(CLASS_SCANNER, DefaultClassScanner.class);
    }

    /**
     * 获取 DataSourceFactory
     * @return DataSourceFactory
     */
    public static DataSourceFactory getDataSourceFactory(){
        return getInstance(DS_FACTORY, DefaultDataSourceFactory.class);
    }

    /**
     * 获取 DataAccessor
     * @return DataAccessor
     */
    public static DataAccessor getDataAccessor(){
        return  getInstance(DATA_ACCESSOR, DefaultDataAccessor.class);
    }

    /**
     * 获取 HandlerMapping
     * @return
     */
    public static HandlerMapping getHandlerMapping(){
        return getInstance(HANDLER_MAPPING, DefaultHandlerMapping.class);
    }

    /**
     * 获取 HandlerInvoker
     * @return
     */
    public static HandlerInvoker getHandlerInvoker(){
        return getInstance(HANDLER_INVOKER, DefaultHandlerInvoker.class);
    }

    /**
     * 获取 HandlerExceptionResolver
     * @return
     */
    public static HandlerExceptionResolver getHandlerExceptionResolver(){
        return getInstance(HNADLER_EXCEPTION_RESOLVER, DefaultHandlerExceptionResolver.class);
    }

    /**
     * 获取 ViewResolver
     * @return
     */
    public static ViewResolver getViewResolver(){
        return getInstance(VIEW_RRSOLVER, DefaultViewResolver.class);
    }

    /**
     * 返回实例
     * @param cacheKey
     * @param defaultImplClass
     * @param <T>
     * @return
     */
    public static <T> T getInstance(String cacheKey,Class<T> defaultImplClass){
        // 若缓存中存在对应的实例，则返回该实例
        if (cache.containsKey(cacheKey)){
            return (T) cache.get(cacheKey);
        }
        // 从配置文件中获取相应的接口实现类配置
        String implClassName = ConfigHelper.getString(cacheKey);
        // 若实现类配置不存在，则使用默认实现类
        if (StringUtil.isEmpty(implClassName)){
            implClassName = defaultImplClass.getName();
        }
        // 通过反射创建改实现类对应的实例
        T instance = ObjectUtil.newInstance(implClassName);
        // 若该实例不为空，则将其放入缓存
        if (instance != null){
            cache.put(cacheKey, instance);
        }
        // 返回该实例
        return instance;
    }

}
