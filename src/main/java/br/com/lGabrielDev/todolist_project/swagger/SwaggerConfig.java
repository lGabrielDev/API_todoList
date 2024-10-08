package br.com.lGabrielDev.todolist_project.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openApiSetup(){
        return new OpenAPI()
            .info(
                new Info()
                    .title("Todo-List API")
                    .description("Todolist aplication using Java + Spring")
                    .version("1.0")
                    .license(
                        new License()
                            .name("MIT license")
                            .url("https://opensource.org/license/mit/")
                    )
                    .contact(new Contact().name("lGabrielDev"))
            )
            .servers(
                List.of(
                    new Server().url("http://localhost:8080")
                    .description("Local Server")
                )
            )
            .tags(
                List.of(
                    new Tag().name("admin").description("admin authority needed"),
                    new Tag().name("persons").description("regular users, without authentication"),
                    new Tag().name("categories").description("from the authenticated person"),
                    new Tag().name("tasks").description("from the authenticated person")
                )
            );
    }
}