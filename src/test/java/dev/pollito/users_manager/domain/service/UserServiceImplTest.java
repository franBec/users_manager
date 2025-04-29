package dev.pollito.users_manager.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.out.UserApiClient;
import dev.pollito.users_manager.domain.port.out.UserMetadataRepository;
import java.util.List;
import java.util.Map;
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
  @Mock private UserMetadataRepository userMetadataRepository;

  @Test
  void shouldReturnUserList_whenFindAll() {
    User user1 = new User();
    user1.setId(1L);

    User user2 = new User();
    user2.setId(2L);

    List<User> users = List.of(user1, user2);

    when(userApiClient.findAll()).thenReturn(users);
    when(userMetadataRepository.findProfilePictureUrlByIds(anyList()))
        .thenReturn(
            Map.of(
                1L, Optional.of("url1"),
                2L, Optional.empty()));

    List<User> result = userService.findAll();
    assertEquals(2, result.size());
    assertEquals("url1", result.get(0).getProfilePictureUrl());
    assertNull(result.get(1).getProfilePictureUrl());
  }

  @Test
  void shouldReturnUser_whenFindById() {
    User user = new User();
    user.setId(1L);

    when(userApiClient.findById(anyLong())).thenReturn(Optional.of(user));
    when(userMetadataRepository.findProfilePictureUrlByIds(anyList()))
        .thenReturn(Map.of(1L, Optional.of("profile-url")));

    User result = userService.findById(1L);
    assertNotNull(result);
    assertEquals("profile-url", result.getProfilePictureUrl());
  }

  @Test
  void shouldSetNoProfilePicture_whenNoneAvailable() {
    User user = new User();
    user.setId(1L);

    when(userApiClient.findById(anyLong())).thenReturn(Optional.of(user));
    when(userMetadataRepository.findProfilePictureUrlByIds(anyList()))
        .thenReturn(Map.of(1L, Optional.empty()));

    User result = userService.findById(1L);
    assertNotNull(result);
    assertNull(result.getProfilePictureUrl());
  }
}
