package org.smart4j.framework.dao;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.InstanceFactory;
import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.ds.DataSourceFactory;
import org.smart4j.framework.util.ClassUtil;

import javax.sql.DataSource;
import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 封装数据库相关的操作
 */
public class DataSourceHelper {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceHelper.class);

    /**
     * 定义一个局部线程变量（使每个线程都有自己的连接）
     */
    private static final ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    /**
     * 获取数据源工厂
     */
    private static final DataSourceFactory dataSourceFactory = InstanceFactory.getDataSourceFactory();

    /**
     * 获取数据库访问器
     */
    private static final DataAccessor dataAccessor =  InstanceFactory.getDataAccessor();

    /**
     * 数据库类型
     */
    private static final String databaseType = ConfigHelper.getString("smart.framework.jdbc.type");

    /**
     * 获取数据库类型
     * @return
     */
    public static String getDatabaseType(){
        return databaseType;
    }

    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource(){
        return dataSourceFactory.getDataSource();
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn;
        try {
            // 先从 ThreadLocal 中获取 Connection
            conn = connContainer.get();
            if (conn == null){
                // 若不存在，则从 DataSource 中获取 Connection
                conn = getDataSource().getConnection();
                //将 Connection 放入 ThreadLocal 中
                if (conn != null){
                    connContainer.set(conn);
                }
            }
        }catch (SQLException e){
            logger.error("获取数据库连接出错！",e);
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction(){
        Connection conn = getConnection();
        if (conn != null){
            try {
                conn.setAutoCommit(false);
            }catch (SQLException e){
                logger.error("开启事务出错！",e);
                throw new RuntimeException(e);
            }finally {
                connContainer.set(conn);
            }
        }
    }

    /**
     * 提交事务
     */
    public static void commitTransaction(){
        Connection conn = getConnection();
        if (conn != null){
            try {
                conn.commit();
                conn.close();
            }catch (SQLException e){
                logger.error("提交事务出错！",e);
                throw new RuntimeException(e);
            }finally {
                connContainer.remove();
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollTransaction(){
        Connection conn = getConnection();
        if (conn != null){
            try {
                conn.rollback();
                conn.close();
            }catch (SQLException e){
                logger.error("回滚事务出错！",e);
                throw new RuntimeException(e);
            }finally {
                connContainer.remove();
            }
        }
    }

    /**
     * 初始化 SQL 脚本
     * @param sqlPath
     */
    public static void initSQL(String sqlPath){
        try {
            File sqlFile = new File(ClassUtil.getClassPath() + sqlPath);
            List<String> sqlList = FileUtils.readLines(sqlFile);
            for (String sql : sqlList){
                update(sql);
            }
        }catch (Exception e){
            logger.error("初始化 SQL 脚本出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据 SQL 语句查询 Entity
     * @param entityClass
     * @param sql
     * @param params
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params){
        return dataAccessor.queryEntity(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Entity 列表
     * @param entityClass
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityList(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Entity 映射（Field Name => Field Value）
     * @param entityClass
     * @param sql
     * @param params
     * @return
     */
    public static <K, V> Map<K, V> queryEntityMap(Class<V> entityClass, String sql, Object... params) {
        return dataAccessor.queryEntityMap(entityClass, sql, params);
    }

    /**
     * 根据 SQL 语句查询 Array 格式的字段（单条记录）
     * @param sql
     * @param params
     * @return
     */
    public static Object[] queryArray(String sql, Object... params) {
        return dataAccessor.queryArray(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Array 格式的字段列表（多条记录）
     * @param sql
     * @param params
     * @return
     */
    public static List<Object[]> queryArrayList(String sql, Object... params) {
        return dataAccessor.queryArrayList(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Map 格式的字段（单条记录）
     * @param sql
     * @param params
     * @return
     */
    public static Map<String, Object> queryMap(String sql, Object... params) {
        return dataAccessor.queryMap(sql, params);
    }

    /**
     * 根据 SQL 语句查询 Map 格式的字段列表（多条记录）
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> queryMapList(String sql, Object... params) {
        return dataAccessor.queryMapList(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段（单条记录）
     * @param sql
     * @param params
     * @return
     */
    public static <T> T queryColumn(String sql, Object... params) {
        return dataAccessor.queryColumn(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段列表（多条记录）
     * @param sql
     * @param params
     * @return
     */
    public static <T> List<T> queryColumnList(String sql, Object... params) {
        return dataAccessor.queryColumnList(sql, params);
    }

    /**
     * 根据 SQL 语句查询指定字段映射（多条记录）
     * @param sql
     * @param params
     * @return
     */
    public static <T> Map<T, Map<String, Object>> queryColumnMap(String column, String sql, Object... params) {
        return dataAccessor.queryColumnMap(column, sql, params);
    }

    /**
     * 根据 SQL 语句查询记录条数
     * @param sql
     * @param params
     * @return
     */
    public static long queryCount(String sql, Object... params) {
        return dataAccessor.queryCount(sql, params);
    }

    /**
     * 执行更新语句（包括：update、insert、delete）
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params) {
        return dataAccessor.update(sql, params);
    }

    /**
     * 执行插入语句，返回插入后的主键
     * @param sql
     * @param params
     * @return
     */
    public static Serializable insertReturnPK(String sql, Object... params) {
        return dataAccessor.insertReturnPK(sql, params);
    }

}
