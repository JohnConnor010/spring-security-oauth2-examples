package com.example.springbootoauth2jwt.config;

import com.example.springbootoauth2jwt.model.JwtTokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

@Configuration
@EnableAuthorizationServer
@Slf4j
@ConditionalOnProperty(prefix = "oauth2.clients.config",name = "jdbc",havingValue = "false")
public class AuthServerConfigByMemory extends AuthServerConfig{
    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("postman")
                .secret(passwordEncoder.encode("postman"))
                .scopes("any","all")
                .autoApprove("any")
                .authorizedGrantTypes("password","authorization_code","refresh_token","implicit","client_credentials")
                .redirectUris("http://localhost:8080/redirect")
                .accessTokenValiditySeconds(jwtTokenProperties.getAccessTokenValidity())
                .refreshTokenValiditySeconds(jwtTokenProperties.getRefreshTokenValidity())
                .and()
                .withClient("demo-client")
                .secret(passwordEncoder.encode("demo-client"))
                .scopes("any")
                .authorizedGrantTypes("password","authorization_code","refresh_token","implicit")
                .redirectUris("http://localhost:8080/redirect");
        log.info("OAuth2的Client信息基于内存");
    }

    /*@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.exceptionTranslator(loggingExceptionTranslator());
    }*/

    /*@Bean
    public WebResponseExceptionTranslator loggingExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                e.printStackTrace();
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
            }
        };
    }*/
}
