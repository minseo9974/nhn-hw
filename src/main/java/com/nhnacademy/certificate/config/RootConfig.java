package com.nhnacademy.certificate.config;


import com.nhnacademy.certificate.Base;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class, excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();

        try {
            basicDataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String url = "jdbc:mysql://133.186.241.167:3306/nhn_academy_33";
        String username = "nhn_academy_33";
        String password = "V@B8Rz-hZdINAwjO";

        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxIdle(30);
        basicDataSource.setMinIdle(10);

        basicDataSource.setMaxWaitMillis(5000);
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);

        return basicDataSource;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("message");

        return messageSource;
    }
}
