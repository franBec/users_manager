package dev.pollito.users_manager.service;

import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import java.util.List;

public interface UserService {
  Users findAll(Integer pageNumber, Integer pageSize, List<String> pageSort);

  User findById(Long id);
}
