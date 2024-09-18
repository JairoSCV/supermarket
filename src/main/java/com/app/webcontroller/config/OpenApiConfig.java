package com.app.webcontroller.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        info = @Info(
            title = "API Supermercado",
            description = "This is an API for any supermarkets",
            termsOfService = "www.supermarket.com/terminos_y_servicios",
            version = "1.0.0",
            contact = @Contact(
                name = "Jairo Cornejo",
                url = "www.cornejovega.com/contact",
                email = "cornejovegajairosamir@gmail.com"
            ),
            license = @License(
                name = "APIRESTFUL",
                url = "www.idontknow.com/license"
            )
        ),
        servers = {
            @Server(
                description = "DEV SERVER",
                url = "http://localhost:8080/platzi-market/api"
            ),
            @Server(
                description = "PDN SERVER",
                url = "http://midominio:8080/platzi-market/api"
            ) 
        }
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
            .group("public")
            .pathsToMatch("/**")
            .build();
    }

}
