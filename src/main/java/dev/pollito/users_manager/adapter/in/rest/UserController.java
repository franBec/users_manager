package dev.pollito.users_manager.adapter.in.rest;

import dev.pollito.users_manager.adapter.in.rest.dto.UserResponseDTO;
import dev.pollito.users_manager.adapter.in.rest.mapper.UserMapper;
import dev.pollito.users_manager.domain.port.in.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    return ResponseEntity.ok(userService.getUsers().stream().map(userMapper::map).toList());
  }
}
