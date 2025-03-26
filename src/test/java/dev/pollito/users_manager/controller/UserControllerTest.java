package dev.pollito.users_manager.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserService;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @InjectMocks private UserController userController;
  @Mock private UserService userService;

  @Test
  void whenFindAllThenReturnOk() {
    when(userService.findAll(anyInt(), anyInt(), anyList(), anyString()))
        .thenReturn(mock(Users.class));
    ResponseEntity<Users> response = userController.findAll(0, 0, Collections.emptyList(), "");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response);
  }

  @Test
  void whenFindByIdThenReturnOK() {
    when(userService.findById(anyLong())).thenReturn(mock(User.class));
    ResponseEntity<User> response = userController.findById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }
}
