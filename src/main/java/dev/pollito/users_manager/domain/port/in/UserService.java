package dev.pollito.users_manager.domain.port.in;

import dev.pollito.users_manager.domain.model.User;
import java.util.List;

public interface UserService {
  List<User> getUsers();

  User getUserById(Long id);
}
