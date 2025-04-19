package dev.pollito.users_manager.domain.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = PRIVATE)
public class Company {
  String name;
  String catchPhrase;
  String bs;
}
