package com.gzy.javaoolab;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@EnableConfigurationProperties
@ServletComponentScan(basePackages = "com.gzy.javaoolab.filter")
@MapperScan("com.gzy.javaoolab.dao")
public class JavaOoLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaOoLabApplication.class, args);
	}

}
