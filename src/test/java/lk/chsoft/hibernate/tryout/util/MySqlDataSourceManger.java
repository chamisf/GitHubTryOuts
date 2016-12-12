package lk.chsoft.hibernate.tryout.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;

import java.util.Properties;

/**
 * Database properties and datasource manager fro MySql.
 */
public enum MySqlDataSourceManger {
    MANAGER;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //log settings
        properties.put("hibernate.hbm2ddl.auto", "create");
        //data source settings
        properties.put("hibernate.connection.datasource", getProxyDataSource());

        return properties;
    }

    private ProxyDataSource getProxyDataSource() {
        MysqlDataSource actualDataSource = getMySqlDBDataSource();
        ProxyDataSource proxyDataSource = new ProxyDataSource();
        proxyDataSource.setDataSource(actualDataSource);
        proxyDataSource.setListener(new SLF4JQueryLoggingListener());
        return proxyDataSource;
    }

    private MysqlDataSource getMySqlDBDataSource() {
        MysqlDataSource actualDataSource = new MysqlDataSource();
        actualDataSource.setServerName("127.0.0.1");
        actualDataSource.setPortNumber(3306);
        actualDataSource.setDatabaseName("hibtest");
        actualDataSource.setUser("root");
        actualDataSource.setPassword("root");
        return actualDataSource;
    }
}
