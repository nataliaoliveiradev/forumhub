package forum.hub.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracoesSpringDoc {

    @Bean
    public OpenAPI configurarOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("chave-bearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("API Fórum Hub")
                        .description("API REST para gerenciar o Fórum Hub, incluindo " +
                                "funcionalidades de CRUD para Tópicos e Respostas.")
                        .contact(new Contact()
                                .name("Equipe Backend")
                                .email("backend@forumhub.com"))
                        .license(new License()
                                .name("Licença Apache 2.0")
                                .url("http://forumhub.com/api/licenca")));
    }
}
