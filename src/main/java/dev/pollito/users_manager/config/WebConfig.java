package dev.pollito.users_manager.config;

import dev.pollito.users_manager.config.properties.CorsConfigProperties;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
  private final CorsConfigProperties corsConfigProperties;

  @Override
  public void addCorsMappings(@NotNull CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins(corsConfigProperties.getAllowedOrigins().toArray(new String[0]))
        .allowedMethods(corsConfigProperties.getAllowedMethods().toArray(new String[0]))
        .allowedHeaders(corsConfigProperties.getAllowedHeaders())
        .allowCredentials(corsConfigProperties.getAllowCredentials());
  }
}
