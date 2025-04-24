package dev.pollito.users_manager.adapter.out.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.typicode.jsonplaceholder.api.UserApi;
import com.typicode.jsonplaceholder.model.User;
import dev.pollito.users_manager.adapter.out.rest.mapper.AdapterOutRestUserMapper;
import feign.FeignException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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
  void shouldReturnOptionalUser_whenFindById() {
    when(userApi.findById(anyLong())).thenReturn(mock(User.class));
    assertTrue(userApiClient.findById(1L).isPresent());
  }

  @Test
  void shouldReturnEmptyOptional_whenUserApiClientThrowsFeignExceptionStatusNotFound() {
    FeignException e = mock(FeignException.class);
    when(e.status()).thenReturn(HttpStatus.NOT_FOUND.value());
    when(userApi.findById(anyLong())).thenThrow(e);
    assertTrue(userApiClient.findById(1L).isEmpty());
  }

  @Test
  void shouldThrowFeignException_whenUserApiClientThrowsFeignException() {
    FeignException e = mock(FeignException.class);
    when(e.status()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
    when(userApi.findById(anyLong())).thenThrow(e);
    assertThrows(FeignException.class, () -> userApiClient.findById(1L));
  }
}
