package dev.pollito.users_manager.adapter.in.rest.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UserResponseDTO {
  Long id;
  String name;
  String username;
  String email;
}
