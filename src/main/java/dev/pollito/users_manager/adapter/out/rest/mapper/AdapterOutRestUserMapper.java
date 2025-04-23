package dev.pollito.users_manager.adapter.out.rest.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import dev.pollito.users_manager.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface AdapterOutRestUserMapper {
  User map(com.typicode.jsonplaceholder.model.User u);
}
