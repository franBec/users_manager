package dev.pollito.users_manager.domain.port.in;

import dev.pollito.users_manager.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  Page<User> getUsers(Pageable pageable);

  User getUserById(Long id);
}
