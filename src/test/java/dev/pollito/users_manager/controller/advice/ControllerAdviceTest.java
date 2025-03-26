package dev.pollito.users_manager.controller.advice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.exception.JsonPlaceholderException;
import jakarta.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ExtendWith(MockitoExtension.class)
class ControllerAdviceTest {
  @InjectMocks ControllerAdvice controllerAdvice;

  private static void problemDetailAssertions(
      @NotNull ProblemDetail response, @NotNull Exception e, @NotNull HttpStatus httpStatus) {
    assertEquals(httpStatus.value(), response.getStatus());
    assertEquals(e.getClass().getSimpleName(), response.getTitle());
    assertNotNull(response.getProperties());
    assertNotNull(response.getProperties().get("timestamp"));
    assertNotNull(response.getProperties().get("trace"));
  }

  @Test
  void whenNoResourceFoundExceptionThenReturnProblemDetail() {
    NoResourceFoundException e = mock(NoResourceFoundException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, HttpStatus.NOT_FOUND);
  }

  @Test
  void whenNoSuchElementExceptionThenReturnProblemDetail() {
    NoSuchElementException e = mock(NoSuchElementException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, HttpStatus.NOT_FOUND);
  }

  @Test
  void whenMethodArgumentTypeMismatchExceptionThenReturnProblemDetail() {
    MethodArgumentTypeMismatchException e = mock(MethodArgumentTypeMismatchException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, HttpStatus.BAD_REQUEST);
  }

  @Test
  void whenConstraintViolationExceptionThenReturnProblemDetail() {
    ConstraintViolationException e = mock(ConstraintViolationException.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, HttpStatus.BAD_REQUEST);
  }

  @Test
  void whenExceptionThenReturnProblemDetail() {
    Exception e = mock(Exception.class);
    problemDetailAssertions(controllerAdvice.handle(e), e, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Contract(pure = true)
  private static @NotNull Stream<HttpStatus> httpStatusProvider() {
    return Stream.of(HttpStatus.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ParameterizedTest
  @MethodSource("httpStatusProvider")
  void whenJsonPlaceholderExceptionThenReturnProblemDetail(@NotNull HttpStatus httpStatus) {
    JsonPlaceholderException e = mock(JsonPlaceholderException.class);
    when(e.getStatus()).thenReturn(httpStatus.value());

    problemDetailAssertions(controllerAdvice.handle(e), e, httpStatus);
  }
}
