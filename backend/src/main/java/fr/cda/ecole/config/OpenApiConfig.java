package fr.cda.ecole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Gestion Scolaire")
                        .description("Documentation OpenAPI de l'API REST de gestion scolaire")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipe CDA")
                                .email("contact@cda.local")));
    }
}
