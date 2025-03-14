package dev.pollito.users_manager.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
  Long id;
  String name;
  String username;
  String email;
}
