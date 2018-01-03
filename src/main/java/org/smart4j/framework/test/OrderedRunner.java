package org.smart4j.framework.test;


import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.smart4j.framework.HelperLoader;
import org.smart4j.framework.test.annotation.TestOrder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使测试用例可按顺序执行
 */
public class OrderedRunner extends BlockJUnit4ClassRunner {

    /**
     * 定义一个静态变量，确保 computeTestMethods() 中的排序逻辑只运行一次（JUnit 会调用两次）
     */
    private static List<FrameworkMethod> testMethodList;

    public OrderedRunner(Class<?> cls) throws InitializationError {
        // 调用父类构造器
        super(cls);
        // 初始化 Helper 类
        HelperLoader.init();
    }

    protected List<FrameworkMethod> computeTestMethods(){
        if (testMethodList == null){
            // 获取带有 Test 注解的方法
            testMethodList = super.computeTestMethods();
            // 获取测试方法上的 Order 注解,并对所有的测试方法重新排序
            Collections.sort(testMethodList, new Comparator<FrameworkMethod>() {
                @Override
                public int compare(FrameworkMethod o1, FrameworkMethod o2) {
                    TestOrder t1 = o1.getAnnotation(TestOrder.class);
                    TestOrder t2 = o2.getAnnotation(TestOrder.class);
                    if (t1 == null || t2 == null){
                        return 0;
                    }
                    return t1.value() - t2.value();
                }
            });
        }
        return testMethodList;
    }
}
