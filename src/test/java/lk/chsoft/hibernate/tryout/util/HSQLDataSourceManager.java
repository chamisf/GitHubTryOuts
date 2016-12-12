package lk.chsoft.hibernate.tryout.util;

import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import org.hsqldb.jdbc.JDBCDataSource;

import java.util.Properties;

/**
 * Database properties and datasource manager fro MSSql.
 */
public enum HSQLDataSourceManager {
    MANAGER;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.ejb.use_class_enhancer", Boolean.TRUE.toString());
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        //log settings
        properties.put("hibernate.hbm2ddl.auto", "update");
        //data source settings
        properties.put("hibernate.connection.datasource", getProxyDataSource());

        return properties;
    }

    private ProxyDataSource getProxyDataSource() {
        JDBCDataSource actualDataSource = getHSqlDBDataSource();
        ProxyDataSource proxyDataSource = new ProxyDataSource();
        proxyDataSource.setDataSource(actualDataSource);
        proxyDataSource.setListener(new SLF4JQueryLoggingListener());
        return proxyDataSource;
    }

    private JDBCDataSource getHSqlDBDataSource() {
        JDBCDataSource actualDataSource = new JDBCDataSource();
        actualDataSource.setUrl("jdbc:hsqldb:mem:test");
        actualDataSource.setUser("sa");
        actualDataSource.setPassword("");
        return actualDataSource;
    }
}
