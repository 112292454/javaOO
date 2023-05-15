//package com.gzy.javaoolab.config;
//
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .host("localhost:15511")//可不要
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gzy.javaoolab.controller"))
//                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))          //只扫描有api注解的类
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//只扫描有ApiOperation注解的方法
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("支点-社交")//微服务名称
//                .description("支点-社交接口文档（暂定用户、评论部分）")
//                .version("1.0")
//                .build();
//    }
//}
//
