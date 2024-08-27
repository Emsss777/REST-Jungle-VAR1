package com.epetkov.restjungle.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Welcome to the Jungle!")
                        .version("1.0")
                        .description("Java RESTful API with Spring Boot")
                        .contact(new Contact()
                                .name("Emil Ganchev")
                                .url("https://github.com/Emsss777")
                                .email("emil.p.ganchev@gmail.com")));
    }
}