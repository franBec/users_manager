package dev.pollito.users_manager.adapter.in.rest;

import dev.pollito.users_manager.adapter.in.rest.api.UsersApi;
import dev.pollito.users_manager.adapter.in.rest.dto.User;
import dev.pollito.users_manager.adapter.in.rest.mapper.AdapterInRestUserMapper;
import dev.pollito.users_manager.domain.port.in.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UsersApi {
  private final UserService userService;
  private final AdapterInRestUserMapper adapterInRestUserMapper;

  @Override
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(
        userService.findAll().stream().map(adapterInRestUserMapper::map).toList());
  }

  @Override
  public ResponseEntity<User> findById(Long id) {
    return ResponseEntity.ok(adapterInRestUserMapper.map(userService.findById(id)));
  }
}
