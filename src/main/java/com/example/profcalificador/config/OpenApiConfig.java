package com.example.profcalificador.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(title = "Profesor Rating API", version = "1.0", description = "API para calificar docentes con cooldown de 3 días")
)
public class OpenApiConfig {}
