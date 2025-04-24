package dev.pollito.users_manager.domain.port.out;

import dev.pollito.users_manager.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserApiClient {
  List<User> findAll();

  Optional<User> findById(Long id);
}
