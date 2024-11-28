package com.generation.blogpessoal.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Blog Pessoal")
                        .version("1.0")
                        .description("Documentação interativa para a api do projeto Blog Pessoal")
                        .contact(new Contact()
                        		.name("Matheus Torres")
                        		.url("https://matheustorres.vercel.app/")))
                		.addServersItem(new Server()
                				.url("http://localhost:8080")
                				.description("Servidor de Desenvolvimento"));
    }
}