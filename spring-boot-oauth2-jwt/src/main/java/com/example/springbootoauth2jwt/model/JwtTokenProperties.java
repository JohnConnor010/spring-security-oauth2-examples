package com.example.springbootoauth2jwt.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenProperties {

    private String type;
    private String secret;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String jksKeyFile;
    private String jksStorePassword;
    private String jksKeyPassword;
    private String jksKeyAlias;

    public Resource getJksKeyFileResource(){
        Resource resource;
        if (jksKeyFile.startsWith("classpath:")) {
            resource = new ClassPathResource(jksKeyFile.replace("classpath:",""));
        }else{
            resource = new PathResource(Paths.get(jksKeyFile));
        }
        return resource;
    }
}
