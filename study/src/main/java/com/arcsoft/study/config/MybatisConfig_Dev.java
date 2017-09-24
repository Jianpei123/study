package com.arcsoft.study.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.logging.commons.JakartaCommonsLoggingImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.github.pagehelper.PageInterceptor;
@Profile("dev")
@org.springframework.context.annotation.Configuration
public class MybatisConfig_Dev {
    /*
     * 配置dataSouce
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
    	BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUsername("root");
            basicDataSource.setPassword("123qwe");
            basicDataSource.setUrl(
                    "jdbc:mysql://localhost/onlinelearning?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=true&verifyServerCertificate=false");
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setInitialSize(5);
        return basicDataSource;
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
