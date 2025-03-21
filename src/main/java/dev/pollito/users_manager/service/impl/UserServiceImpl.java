package dev.pollito.users_manager.service.impl;

import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final User USER_1 =
      new User().id(1L).name("Leanne Graham").username("Bret").email("Sincere@april.biz");

  @Override
  public Users findAll(Integer pageNumber, Integer pageSize, List<String> pageSort) {
    return new Users().content(List.of(USER_1));
  }

  @Override
  public User findById(Long id) {
    return USER_1;
  }
}
