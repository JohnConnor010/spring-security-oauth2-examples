package com.example.springbootoauth2jwt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableAuthorizationServer
@ConditionalOnProperty(prefix = "oauth2.clients.config",name = "jdbc",havingValue = "true")
public class AuthServerConfigByJDBC extends AuthServerConfig{
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        log.info("OAuth2的Client信息基于数据库");
    }
}
