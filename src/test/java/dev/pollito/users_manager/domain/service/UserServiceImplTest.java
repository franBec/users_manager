package dev.pollito.users_manager.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.out.UserApiClient;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userService;
  @Mock private UserApiClient userApiClient;

  @Test
  void shouldReturnUserList_whenFindAll() {
    List<User> users = List.of(mock(User.class));
    when(userApiClient.findAll()).thenReturn(users);
    assertEquals(users.size(), userService.findAll().size());
  }

  @Test
  void shouldReturnUser_whenFindById() {
    when(userApiClient.findById(anyLong())).thenReturn(Optional.of(mock(User.class)));
    assertNotNull(userService.findById(1L));
  }
}
