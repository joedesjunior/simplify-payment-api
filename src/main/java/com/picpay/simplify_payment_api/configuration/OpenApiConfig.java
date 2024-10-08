package com.picpay.simplify_payment_api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI baseOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Simplify Payment API Documentation.")
                .version("1.0.0")
                .description("Documentation description"));
  }
}
