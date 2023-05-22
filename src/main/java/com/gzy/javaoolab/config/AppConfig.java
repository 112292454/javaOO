package com.gzy.javaoolab.config;

import com.gzy.javaoolab.utils.FaceModelInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FaceModelInstance faceModelInstance() {
        return FaceModelInstance.getInstance();
    }
}
