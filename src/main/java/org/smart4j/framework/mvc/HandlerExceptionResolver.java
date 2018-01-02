package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler 异常解析器
 */
public interface HandlerExceptionResolver {

    /**
     * 解析 Handler 异常
     * @param request
     * @param response
     * @param e
     */
    void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e);
}
