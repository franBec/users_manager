package dev.pollito.users_manager.adapter.in.rest;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import dev.pollito.users_manager.adapter.in.rest.dto.User;
import dev.pollito.users_manager.adapter.in.rest.mapper.AdapterInRestUserMapper;
import dev.pollito.users_manager.domain.port.in.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
  @InjectMocks private UserController userController;
  @Mock private UserService userService;

  @SuppressWarnings("unused")
  @Spy
  private AdapterInRestUserMapper adapterInRestUserMapper =
      getMapper(AdapterInRestUserMapper.class);

  @Test
  void shouldReturnOk_whenFindAll() {
    when(userService.findAll()).thenReturn(emptyList());
    ResponseEntity<List<User>> response = userController.findAll();
    assertEquals(OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  void shouldReturnOk_whenFindById() {
    when(userService.findById(anyLong()))
        .thenReturn(mock(dev.pollito.users_manager.domain.model.User.class));
    ResponseEntity<User> response = userController.findById(1L);
    assertEquals(OK, response.getStatusCode());
    assertNotNull(response.getBody());
  }
}
