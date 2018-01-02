package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * handler 调用器
 */
public interface HandlerInvoker {

    /**
     * 调用 Handler
     * @param request   请求对象
     * @param response  响应对象
     * @param handler   Handler
     * @throws Exception 异常
     */
    void invokerHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception;
}
