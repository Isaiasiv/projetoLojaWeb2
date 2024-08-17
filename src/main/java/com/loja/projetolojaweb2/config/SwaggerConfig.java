package com.loja.projetolojaweb2.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(new Info().title("Tem de Tudo Roupas API ").version("Beta1.0").
                license(new License().name("Lincença do sistema").url("www.tendetudoroupa.com"))
        );
    }
}
