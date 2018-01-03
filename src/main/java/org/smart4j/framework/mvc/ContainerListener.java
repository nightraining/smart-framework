package org.smart4j.framework.mvc;

import org.smart4j.framework.FrameworkConstant;
import org.smart4j.framework.HelperLoader;
import org.smart4j.framework.plugin.Plugin;
import org.smart4j.framework.plugin.PluginHelper;
import org.smart4j.framework.plugin.WebPlugin;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.util.List;

/**
 * 容器监听器
 */
public class ContainerListener implements ServletContextListener {
    /**
     * 当容器初始化时调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取 ServletContext
        ServletContext servletContext = sce.getServletContext();
        // 初始化相关的 Helper 类
        HelperLoader.init();
        // 添加 Servlet 映射
        addServletMapping(servletContext);
        // 注册 WebPlugin
        registerWebPlugin(servletContext);
    }

    /**
     * 当容器销毁时调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 销毁插件
        destoroyPlugin();
    }

    /**
     * 添加 Servlet 映射
     * @param context
     */
    private void addServletMapping(ServletContext context) {
        // 用 DefaultServlet 映射所有静态资源
        registerDefaultServlet(context);
        // 用 JspServlet 映射所有 JSP 请求
        registerJspServlet(context);
    }

    /**
     * 用 DefaultServlet 映射所有静态资源
     * @param context
     */
    private void registerDefaultServlet(ServletContext context) {
        ServletRegistration defaultServlet = context.getServletRegistration("default");
        defaultServlet.addMapping("/index.html");
        defaultServlet.addMapping("/favicon.ico");
        String wwwPath = FrameworkConstant.WWW_PATH;
        if (StringUtil.isNotEmpty(wwwPath)){
            defaultServlet.addMapping(wwwPath + "*");
        }
    }

    /**
     *  用 JspServlet 映射所有 JSP 请求
     * @param context
     */
    private void registerJspServlet(ServletContext context) {
        ServletRegistration jspServlet = context.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        String jspPath = FrameworkConstant.JSP_PATH;
        if (StringUtil.isNotEmpty(jspPath)){
            jspServlet.addMapping(jspPath + "*");
        }
    }

    /**
     * 注册 WebPlugin
     * @param context
     */
    private void registerWebPlugin(ServletContext context) {
        List<Plugin> pluginList = PluginHelper.getPluginList();
        for (Plugin plugin : pluginList){
            if (plugin instanceof WebPlugin){
                WebPlugin webPlugin = (WebPlugin) plugin;
                webPlugin.register(context);
            }
        }
    }

    /**
     * 销毁插件
     */
    private void destoroyPlugin() {
        List<Plugin> pluginList = PluginHelper.getPluginList();
        for (Plugin plugin : pluginList){
            plugin.destory();
        }
    }
}
