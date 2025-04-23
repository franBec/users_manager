package dev.pollito.users_manager.config.feign;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class FeignException extends RuntimeException {
  int status;
  String reason;

  public FeignException(int status, String reason) {
    super(reason);
    this.status = status;
    this.reason = reason;
  }
}
