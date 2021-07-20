package br.com.aluraflix.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.function.Predicate;

@Configuration
public class SwaggerConfiguration {

   public Docket configureSwagger() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.basePackage("br.com.aluraflix.domain"))
               .paths(Predicate.not(PathSelectors.regex("/error.*")))
               .build()
               .apiInfo(apiInfo());
   }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API Alura Flix")
                .description("API REST da aplicação AluraFlix - Challenge Backend Alura")
                .termsOfServiceUrl("https://www.alura.com.br/")
                .version("1.0.0")
                .contact(new Contact("Guilherme Reis", "https://github.com/guilhermereis14", "guilhermereis14@gmail.com"))
                .build();
    }

}
