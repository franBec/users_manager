package exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JsonPlaceholderException extends RuntimeException {
  private final int status;
}
