package dev.pollito.users_manager.adapter.in.rest.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.pollito.users_manager.adapter.in.rest.dto.UserResponseDTO;
import dev.pollito.users_manager.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface UserMapper {
  UserResponseDTO map(User u);
}
