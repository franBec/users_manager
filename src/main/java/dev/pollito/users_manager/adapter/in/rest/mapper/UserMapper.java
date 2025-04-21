package dev.pollito.users_manager.adapter.in.rest.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.pollito.users_manager.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface UserMapper {
  dev.pollito.users_manager.adapter.in.rest.dto.User map(User u);
}
