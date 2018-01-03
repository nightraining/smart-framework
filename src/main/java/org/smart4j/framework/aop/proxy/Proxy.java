package org.smart4j.framework.aop.proxy;

/**
 * 代理接口
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain 代理链
     * @return 目标方代理返回值
     * @throws Throwable 异常
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
