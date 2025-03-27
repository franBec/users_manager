package dev.pollito.users_manager.service.impl;

import static dev.pollito.users_manager.util.PageUtil.createPageable;

import dev.pollito.users_manager.mapper.UserMapper;
import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.repository.UserRepository;
import dev.pollito.users_manager.service.UserService;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public Users findAll(Integer pageNumber, Integer pageSize, List<String> pageSort, String q) {
    return userMapper.map(
        userRepository.findAllByQueryContainingIgnoreCase(
            createPageable(pageNumber, pageSize, pageSort), q));
  }

  @Override
  public User findById(Long id) {
    return userMapper.map(userRepository.findById(id).orElseThrow());
  }
}
