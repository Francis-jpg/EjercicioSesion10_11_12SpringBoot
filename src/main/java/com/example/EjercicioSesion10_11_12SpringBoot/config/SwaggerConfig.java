package com.example.EjercicioSesion10_11_12SpringBoot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
*
* http://localhost:8081/swagger-ui/
 *
 * Implementamos la nomenclatura de configuración
 *
 * @author Franccisco Molina Jurado
 *
 *
*
* */
@Configuration
public class SwaggerConfig {

    //Creamos un método para el docket (que no el docket de contenedores)

    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiDetails() {

        return new ApiInfo("Spring Boot Laptop Api Rest",
                "Librería Api rest doct",
                "1.0",
                "https://www.google.com",
                new Contact("Francisco", "http://www.google.com", "fran@examplle.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());

    }

    //Solucionar el conflicto de arranque de la web
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }




}
