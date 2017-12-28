package org.smart4j.framework.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.dao.DataAccessor;
import org.smart4j.framework.dao.DataSourceHelper;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 默认数据库访问器
 * 基于 Apache Commons DbUtils 实现
 */
public class DefaultDataAccessor implements DataAccessor {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataAccessor.class);

    private final QueryRunner queryRunner;

    public DefaultDataAccessor(){
        DataSource dataSource = DataSourceHelper.getDataSource();
        queryRunner = new QueryRunner(dataSource);
    }

    @Override
    public <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return null;
    }

    @Override
    public Object[] queryArray(String sql, Object... params) {
        return new Object[0];
    }

    @Override
    public List<Object[]> queryArrayList(String sql, Object... params) {
        return null;
    }

    @Override
    public Map<String, Object> queryMap(String sql, Object... params) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> T queryColumn(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> List<T> queryColumnList(String sql, Object... params) {
        return null;
    }

    @Override
    public <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return null;
    }

    @Override
    public long queryCount(String sql, Object... params) {
        return 0;
    }

    @Override
    public int update(String sql, Object... params) {
        return 0;
    }

    @Override
    public Serializable insertReturnPK(String sql, Object params) {
        return null;
    }
}
