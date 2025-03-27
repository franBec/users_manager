package dev.pollito.users_manager.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = SPRING)
public interface UserMapper {
  User map(dev.pollito.users_manager.entity.User user);

  Users map(Page<dev.pollito.users_manager.entity.User> userPage);
}
