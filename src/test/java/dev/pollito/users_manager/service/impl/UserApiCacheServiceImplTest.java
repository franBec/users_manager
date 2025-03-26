package dev.pollito.users_manager.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.typicode.jsonplaceholder.api.UserApi;
import com.typicode.jsonplaceholder.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserApiCacheServiceImplTest {
  @InjectMocks private UserApiCacheServiceImpl userApiCacheService;
  @Mock private UserApi userApi;

  @Test
  void whenGetUsersThenReturnListOfUsers() {
    when(userApi.getUsers()).thenReturn(List.of(mock(User.class)));
    assertFalse(userApiCacheService.getUsers().isEmpty());
  }
}
