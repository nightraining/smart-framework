package org.smart4j.framework.ds;

import javax.sql.DataSource;

/**
 * 获取源工厂
 */
public interface DataSourceFactory {

    DataSource getDataSource();
}
