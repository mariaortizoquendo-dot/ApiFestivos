package festivos.api.presentacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configurarOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Festivos")
                        .description("API para la gestión y consulta de días festivos")
                        .version("1.0.0")
                );
    }
}
