package dev.pollito.users_manager.controller;

import dev.pollito.users_manager.api.UsersApi;
import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {
  private final UserService userService;

  @Override
  public ResponseEntity<Users> findAll(
      Integer pageNumber, Integer pageSize, List<String> pageSort) {
    return ResponseEntity.ok(userService.findAll(pageNumber, pageSize, pageSort));
  }

  @Override
  public ResponseEntity<User> findById(Long id) {
    return ResponseEntity.ok(userService.findById(id));
  }
}
