package com.example.springbootoauth2jwt.config;

import com.example.springbootoauth2jwt.model.JwtTokenProperties;
import com.example.springbootoauth2jwt.service.impl.JWTTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
public class JwtTokenConfig {

    @Autowired
    private JwtTokenProperties jwtTokenProperties;

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        String type = jwtTokenProperties.getType();
        switch (type) {
            case "secret":
                tokenConverter.setSigningKey(jwtTokenProperties.getSecret());
                tokenConverter.setVerifierKey(jwtTokenProperties.getSecret());
                break;
            case "jks":
                KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(jwtTokenProperties.getJksKeyFileResource(),
                        jwtTokenProperties.getJksKeyPassword().toCharArray());
                tokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtTokenProperties.getJksKeyAlias(),jwtTokenProperties.getJksKeyPassword().toCharArray()));
                break;
            default:
                throw new RuntimeException("请正确的配置密钥类型:secret、jks");
        }
        return tokenConverter;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return new JWTTokenEnhancer();
    }
}
