package com.banary.admin.config;

import com.banary.admin.annotation.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by xiyongchun on 2016/8/30.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.banary.admin.mapper", annotationClass= Mapper.class, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatiesConfig {

    @Primary
    @Bean(name="datasource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * @Description: (配置SqlSessionFactory)
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource")DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*.xml"));
        return sessionFactory.getObject();
    }

    /**
     * @Description: (配置SqlSessionTemplate)
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name="sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
