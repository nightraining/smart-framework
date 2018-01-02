package org.smart4j.framework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.aop.proxy.Proxy;
import org.smart4j.framework.aop.proxy.ProxyChain;

import java.lang.reflect.Method;


/**
 * 切面代理
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger logger = LoggerFactory.getLogger(AspectProxy.class);

    public final Object doProxy(ProxyChain proxyChain) throws Throwable{
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            if (intercept(cls, method, params)){
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            }
        } catch (Exception e){
            logger.error("AOP 异常",e);
            error(cls, method, params, e);
        } finally {
            end();
        }
        return result;
    }




    public void begin(){
    }

    protected boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable{
        return true;
    }

    private void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    }

    private void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable{
    }

    private void error(Class<?> cls, Method method, Object[] params, Exception e) {
    }

    protected void end(){

    }

}
