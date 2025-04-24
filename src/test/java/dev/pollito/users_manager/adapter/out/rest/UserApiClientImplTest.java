package dev.pollito.users_manager.adapter.out.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.typicode.jsonplaceholder.api.UserApi;
import com.typicode.jsonplaceholder.model.User;
import dev.pollito.users_manager.adapter.out.rest.mapper.AdapterOutRestUserMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserApiClientImplTest {
  @InjectMocks private UserApiClientImpl userApiClient;
  @Mock private UserApi userApi;

  @SuppressWarnings("unused")
  @Spy
  private AdapterOutRestUserMapper adapterOutRestUserMapper =
      getMapper(AdapterOutRestUserMapper.class);

  @Test
  void shouldReturnUserList_whenFindAll() {
    List<User> users = List.of(mock(User.class));
    when(userApi.findAll()).thenReturn(users);
    assertEquals(users.size(), userApiClient.findAll().size());
  }

  @Test
  void shouldReturnUser_whenFindById() {
    when(userApi.findById(anyLong())).thenReturn(mock(User.class));
    assertNotNull(userApiClient.findById(1L));
  }
}
