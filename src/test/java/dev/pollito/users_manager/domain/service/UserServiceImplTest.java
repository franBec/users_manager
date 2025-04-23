package dev.pollito.users_manager.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userService;

  @Test
  void shouldReturnUsersList_whenFindAll() {
    assertNotNull(userService.findAll());
  }

  @Test
  void shouldThrowNotImplementedException_whenFindById() {
    assertThrows(NotImplementedException.class, () -> userService.findById(-1L));
  }
}
