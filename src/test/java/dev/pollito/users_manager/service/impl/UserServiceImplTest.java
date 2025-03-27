package dev.pollito.users_manager.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.entity.User;
import dev.pollito.users_manager.mapper.UserMapper;
import dev.pollito.users_manager.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userService;
  @Mock private UserRepository userRepository;

  @SuppressWarnings("unused")
  @Spy
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @Test
  void whenFindAllThenReturnUsers() {
    when(userRepository.findAllByQueryContainingIgnoreCase(any(Pageable.class), anyString()))
        .thenReturn((new PageImpl<>(List.of(), PageRequest.of(0, 10), 0)));
    assertNotNull(userService.findAll(0, 1, Collections.emptyList(), ""));
  }

  @Test
  void whenGetUserThenReturnUser() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
    assertNotNull(userService.findById(1L));
  }
}
