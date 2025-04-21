package dev.pollito.users_manager.domain.service;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.in.UserService;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private static final User USER_1 =
      User.builder()
          .id(1L)
          .name("Leanne Graham")
          .username("Bret")
          .email("Sincere@april.biz")
          .build();

  @Override
  public List<User> getUsers() {
    return List.of(USER_1);
  }

  @Override
  public User getUserById(Long id) {
    throw new NotImplementedException();
  }
}
