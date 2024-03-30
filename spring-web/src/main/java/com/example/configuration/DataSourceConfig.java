package com.example.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.db2.mapper",sqlSessionTemplateRef = "secondSqlSessionTemplate")
@MapperScan(basePackages = "com.example.db1.mapper",sqlSessionTemplateRef = "firstSqlSessionTemplate")
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Bean(name = "primaryDataSource",initMethod = "init")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    @Bean(name = "secondaryDataSource",initMethod = "init")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    // 创建多个SqlSessionFactoryBean以绑定不同的数据源
    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfiguration(configuration);
        // 设置其他MyBatis配置，如：MapperLocations等
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String mapperLocation = "classpath:mappers/db1/*.xml";
        factoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        return factoryBean.getObject();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String mapperLocation = "classpath:mappers/db2/*.xml";
        factoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        // 同样的配置，也可以根据需求添加
        return factoryBean.getObject();
    }


    @Bean(name = "firstSqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondaryDataSource") DataSource data){
        return new DataSourceTransactionManager(data);
    }

    @Bean(name = "firstTransactionTemplate")
    public TransactionTemplate firstTransactionTemplate(@Qualifier("firstTransactionManager") PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate;
    }

    @Bean(name = "secondTransactionTemplate")
    public TransactionTemplate secondTransactionTemplate(@Qualifier("secondTransactionManager") PlatformTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        return transactionTemplate;
    }


//    @Bean(name = "firstMapperScannerConfigurer")
//    public MapperScannerConfigurer firstMapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.example.db1.mapper");
//        return mapperScannerConfigurer;
//    }
//    @Bean(name = "secondMapperScannerConfigurer")
//    public MapperScannerConfigurer secondMapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("secondarySqlSessionFactory");
//        mapperScannerConfigurer.setBasePackage("com.example.db2.mapper");
//        return mapperScannerConfigurer;
//    }



    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

}
