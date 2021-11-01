package com.example.springbootoauth2jwt.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "security")
@Component
public class CustomSecurityProperties {
    private String[] permitAll;
    private String[] ignoring;
}
