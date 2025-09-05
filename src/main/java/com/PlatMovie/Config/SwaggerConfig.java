package com.PlatMovie.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI(){

        Info info = new Info();
        info.title("PlatMovie");
        info.version("v1");
        info.description("Aplicação para gerenciamento de catalogo de filmes");
        //dado vazio apenas para lembrar da funcao
        info.contact(new Contact());

        return new OpenAPI().info(info);
    }

}
