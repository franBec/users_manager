package dev.pollito.users_manager.service;

import dev.pollito.users_manager.model.User;
import java.util.List;

public interface UserService {
  List<User> getUsers();
}
