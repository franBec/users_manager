package dev.pollito.users_manager.api.config;

import static feign.Logger.Level.FULL;

import com.typicode.jsonplaceholder.api.UserApi;
import dev.pollito.users_manager.config.properties.JsonPlaceholderConfigProperties;
import dev.pollito.users_manager.errordecoder.JsonPlaceholderErrorDecoder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(
    value = {
      @ComponentScan(
          basePackages = {
            "com.typicode.jsonplaceholder.api",
          })
    })
@RequiredArgsConstructor
public class JsonPlaceholderApiConfig {
  private final JsonPlaceholderConfigProperties jsonPlaceholderConfigProperties;

  @Bean
  public UserApi userApi() {
    return Feign.builder()
        .client(new OkHttpClient())
        .encoder(new GsonEncoder())
        .decoder(new GsonDecoder())
        .errorDecoder(new JsonPlaceholderErrorDecoder())
        .logger(new Slf4jLogger(UserApi.class))
        .logLevel(FULL)
        .target(UserApi.class, jsonPlaceholderConfigProperties.getBaseUrl());
  }
}
