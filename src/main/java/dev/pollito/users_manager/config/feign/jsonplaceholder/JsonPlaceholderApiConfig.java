package dev.pollito.users_manager.config.feign.jsonplaceholder;

import com.typicode.jsonplaceholder.ApiClient;
import com.typicode.jsonplaceholder.api.UserApi;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JsonPlaceholderApiConfig {
  private final JsonPlaceholderConfigProperties jsonPlaceholderConfigProperties;
  private final JsonPlaceholderErrorDecoder jsonPlaceholderErrorDecoder;

  @Bean
  public ApiClient apiClient() {
    ApiClient apiClient = new ApiClient();
    apiClient.setBasePath(jsonPlaceholderConfigProperties.getBaseUrl());
    apiClient.setFeignBuilder(
        apiClient.getFeignBuilder().errorDecoder(jsonPlaceholderErrorDecoder));

    return apiClient;
  }

  @Bean
  public UserApi userApi(@NotNull ApiClient apiClient) {
    return apiClient.buildClient(UserApi.class);
  }
}
