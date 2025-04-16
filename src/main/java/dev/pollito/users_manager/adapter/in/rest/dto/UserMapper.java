package dev.pollito.users_manager.adapter.in.rest.dto;

import static java.util.Objects.isNull;

import dev.pollito.users_manager.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public UserResponseDTO map(User user) {
    if (isNull(user)) {
      return null;
    }

    return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
  }
}
