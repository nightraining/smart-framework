package org.smart4j.framework.mvc;

public interface HandlerMapping {

    /**
     * 获取 Handler
     * @param currentRequestMethod
     * @param currentRequestPath
     * @return
     */
    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
