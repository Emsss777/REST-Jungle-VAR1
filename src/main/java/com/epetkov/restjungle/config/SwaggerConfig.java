package com.epetkov.restjungle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String JUNGLE_TAG = "Jungle Service";
    public static final String SWAGGER_TAG = "Swagger Service";

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag(JUNGLE_TAG, "--- This is my Jungle Controller"),
                        new Tag(SWAGGER_TAG, "--- This is my Swagger Controller")
                )
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData(){

        Contact contact = new Contact("Emil Ganchev",
                "https://github.com/Emsss777",
                "emil.p.ganchev@gmail.com");

        return new ApiInfo(
                "Welcome to the Jungle!",
                "Java RESTful API with Spring Boot",
                "1.0",
                "https://github.com/Emsss777/REST-Jungle-VAR1",
                contact,
                "Jungle License - Version 2.0",
                "https://github.com/Emsss777/REST-Jungle-VAR2",
                new ArrayList<>());
    }
}
