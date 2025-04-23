package dev.pollito.users_manager.domain.port.out;

import dev.pollito.users_manager.domain.model.User;
import java.util.List;

public interface UserApiClient {
  List<User> findAll();

  User findById(Long id);
}
