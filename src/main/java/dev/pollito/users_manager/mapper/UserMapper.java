package dev.pollito.users_manager.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.pollito.users_manager.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface UserMapper {
  User map(com.typicode.jsonplaceholder.model.User user);

  List<User> map(List<com.typicode.jsonplaceholder.model.User> users);
}
