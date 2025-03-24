package dev.pollito.users_manager.config;

import static java.util.concurrent.TimeUnit.HOURS;

import com.github.benmanes.caffeine.cache.Caffeine;
import dev.pollito.users_manager.config.properties.JsonPlaceholderConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class CacheConfig {
  private final JsonPlaceholderConfigProperties jsonPlaceholderConfigProperties;
  public static final String JSON_PLACEHOLDER_CACHE = "JSON_PLACEHOLDER_CACHE";

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(JSON_PLACEHOLDER_CACHE);
    caffeineCacheManager.setCaffeine(
        Caffeine.newBuilder()
            .expireAfterWrite(jsonPlaceholderConfigProperties.getExpiresAfter(), HOURS));

    return caffeineCacheManager;
  }
}
