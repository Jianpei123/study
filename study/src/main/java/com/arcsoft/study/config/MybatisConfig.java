package com.arcsoft.study.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@org.springframework.context.annotation.Configuration
public class MybatisConfig {
    /*
     * 配置dataSouce
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setUser("root");
//            comboPooledDataSource.setPassword("root");
            comboPooledDataSource.setPassword("As1995817.");
            comboPooledDataSource.setJdbcUrl(
                    "jdbc:mysql://localhost:3306/onlinelearning?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true");
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setInitialPoolSize(20);
            comboPooledDataSource.setMaxPoolSize(10);
            comboPooledDataSource.setMinPoolSize(5);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    /*
     * 配置sqlSessionFactory
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, this.dataSource());

        Configuration configuration = new Configuration(environment);
        configuration.setMapUnderscoreToCamelCase(true);
        // 配置分页插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = System.getProperties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.dataSource());
        factoryBean.setConfiguration(configuration);
        factoryBean.setPlugins(new Interceptor[] { pageInterceptor });
        return factoryBean;
    }
}
