package com.spring.webmvc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

//DB 접속 관련 설정(접속, 커넥션 풀)
@Configuration
@PropertySource("classpath:db_info.properties")
public class DatabaseConfig {
    @Value("${local.db.userName}")
    private String userName;
    @Value("${local.db.password}")
    private String password;
    @Value("${local.db.url}")
    private String url;

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(userName);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        return new HikariDataSource(config);
    }
}
