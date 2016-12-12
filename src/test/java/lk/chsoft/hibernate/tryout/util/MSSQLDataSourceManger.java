package lk.chsoft.hibernate.tryout.util;

//import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import net.ttddyy.dsproxy.listener.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSource;

import java.util.Properties;

/**
 * Database properties and datasource manager fro MSSql.
 */
public enum MSSQLDataSourceManger {
//    MANAGER;
//
//    public Properties getProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
//        //log settings
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        //data source settings
//        properties.put("hibernate.connection.datasource", getProxyDataSource());
//
//        return properties;
//    }
//
//    private ProxyDataSource getProxyDataSource() {
//        SQLServerDataSource actualDataSource = getMSSqlDBDataSource();
//        ProxyDataSource proxyDataSource = new ProxyDataSource();
//        proxyDataSource.setDataSource(actualDataSource);
//        proxyDataSource.setListener(new SLF4JQueryLoggingListener());
//        return proxyDataSource;
//    }
//
//    private SQLServerDataSource getMSSqlDBDataSource() {
//        SQLServerDataSource actualDataSource = new SQLServerDataSource();
//        actualDataSource.setServerName("localhost");
//        actualDataSource.setPortNumber(1433);
//        actualDataSource.setDatabaseName("test");
//        actualDataSource.setUser("sa");
//        actualDataSource.setPassword("csfcse86");
//        return actualDataSource;
//    }
}
