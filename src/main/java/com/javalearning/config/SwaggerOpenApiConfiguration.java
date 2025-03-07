package com.javalearning.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerOpenApiConfiguration {

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Java learning title"))
                .servers(List.of(new Server().url("http://localhost:8080")
                                .description(" its local"),
                        new Server().url("http://dev-app")
                                .description(" its dev")))
                ;
    }
}
