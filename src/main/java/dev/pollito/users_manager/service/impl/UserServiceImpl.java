package dev.pollito.users_manager.service.impl;

import dev.pollito.users_manager.mapper.UserMapper;
import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserApiCacheService;
import dev.pollito.users_manager.service.UserService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserApiCacheService userApi;
  private final UserMapper userMapper;

  @Override
  public Users findAll(Integer pageNumber, Integer pageSize, List<String> pageSort) {
    return new Users().content(userMapper.map(userApi.getUsers()));
  }

  @Override
  public User findById(Long id) {
    return userMapper.map(
        userApi.getUsers().stream()
            .filter(u -> Objects.nonNull(u.getId()))
            .filter(u -> u.getId() == id.intValue())
            .findFirst()
            .orElseThrow());
  }
}
