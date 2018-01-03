package org.smart4j.framework.plugin;

/**
 * 插件接口
 */
public interface Plugin {

    /**
     * 初始化插件
     */
    void init();

    /**
     * 销毁插件
     */
    void destory();
}
