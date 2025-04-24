package dev.pollito.users_manager.domain.service;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.in.UserService;
import dev.pollito.users_manager.domain.port.out.UserApiClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserApiClient userApiClient;

  @Override
  public List<User> findAll() {
    return userApiClient.findAll();
  }

  @Override
  public User findById(Long id) {
    return userApiClient.findById(id).orElseThrow();
  }
}
