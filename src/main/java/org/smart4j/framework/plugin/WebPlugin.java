package org.smart4j.framework.plugin;

import javax.servlet.ServletContext;

/**
 * 基于 Web 的插件抽象实现，拥有 Plugin 接口的所有功能
 *
 * 可在子类中注册 Servlet、Filter、Listener 等
 */
public abstract class WebPlugin implements Plugin{

    public void init(){
    }

    public void destory(){
    }

    public abstract void register(ServletContext servletContext);

}
