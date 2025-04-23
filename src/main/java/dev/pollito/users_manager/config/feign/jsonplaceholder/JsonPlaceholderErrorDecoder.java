package dev.pollito.users_manager.config.feign.jsonplaceholder;

import dev.pollito.users_manager.config.feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceholderErrorDecoder implements ErrorDecoder {
  @Override
  public Exception decode(String s, @NotNull Response response) {
    return new FeignException(response.status(), response.reason());
  }
}
