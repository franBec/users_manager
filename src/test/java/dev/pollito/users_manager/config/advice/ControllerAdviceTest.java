package dev.pollito.users_manager.config.advice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.http.HttpStatus.*;

import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ExtendWith(MockitoExtension.class)
class ControllerAdviceTest {
  @InjectMocks private ControllerAdvice controllerAdvice;

  private static void problemDetailAssertions(
      @NotNull ProblemDetail response, @NotNull Exception e, @NotNull HttpStatus httpStatus) {
    assertEquals(httpStatus.value(), response.getStatus());
    assertEquals(e.getClass().getSimpleName(), response.getTitle());
    assertNotNull(response.getProperties());
    assertNotNull(response.getProperties().get("timestamp"));
    assertNotNull(response.getProperties().get("trace"));
  }

  @Test
  void givenException_whenHandle_thenReturnsProblemDetail() {
    Exception e = mock(Exception.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, INTERNAL_SERVER_ERROR);
  }

  @Test
  void givenMethodArgumentTypeMismatchException_whenHandle_thenReturnsProblemDetail() {
    MethodArgumentTypeMismatchException e = mock(MethodArgumentTypeMismatchException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, BAD_REQUEST);
  }

  @Test
  void givenNoResourceFoundException_whenHandle_thenReturnsProblemDetail() {
    NoResourceFoundException e = mock(NoResourceFoundException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, NOT_FOUND);
  }

  @Test
  void givenNoSuchElementException_whenHandle_thenReturnsProblemDetail() {
    NoSuchElementException e = mock(NoSuchElementException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, NOT_FOUND);
  }
}
