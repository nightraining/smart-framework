package org.smart4j.framework.tx;

import org.slf4j.LoggerFactory;
import org.smart4j.framework.aop.proxy.Proxy;
import org.smart4j.framework.aop.proxy.ProxyChain;
import org.smart4j.framework.dao.DatabaseHelper;
import org.smart4j.framework.tx.annotation.Transaction;

import java.lang.reflect.Method;

/**
 * 事务代理
 */
public class TransactionProxy implements Proxy {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionProxy.class);

    /**
     * 定义一个线程局部变量，用于保存当前线程中是否进行了事务处理，默认为 false（未处理）
     */
    private static final ThreadLocal<Boolean> flagContains = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        // 判断当前线程是否进行了事务处理
        boolean flag = flagContains.get();
        // 获取目标方法
        Method method = proxyChain.getTargetMethod();
        // 若当前想成未进行是事务处理，且在目标方法上定义了 Transaction 注解，则说明该方法需进行事务处理
        if (!flag && method.isAnnotationPresent(Transaction.class)){
            // 设置当前线程已进行事务处理
            flagContains.set(true);
            try {
                // 开启事务
                DatabaseHelper.beginTransaction();
                logger.debug("开启事务！");
                // 执行目标方法
                result = proxyChain.doProxyChain();
                // 提交事务
                DatabaseHelper.commitTransaction();
                logger.debug("提交事务！");
            } catch (Exception e){
                // 回滚事务
                DatabaseHelper.rollTransaction();
                logger.debug("回滚事务！");
                throw e;
            } finally {
                // 移除线程局部变量
                flagContains.remove();
            }
        } else {
            // 执行目标方法
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
