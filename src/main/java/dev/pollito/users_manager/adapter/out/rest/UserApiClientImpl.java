package dev.pollito.users_manager.adapter.out.rest;

import com.typicode.jsonplaceholder.api.UserApi;
import dev.pollito.users_manager.adapter.out.rest.mapper.AdapterOutRestUserMapper;
import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.out.UserApiClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApiClientImpl implements UserApiClient {
  private final UserApi userApi;
  private final AdapterOutRestUserMapper adapterOutRestUserMapper;

  @Override
  public List<User> findAll() {
    return userApi.findAll().stream().map(adapterOutRestUserMapper::map).toList();
  }

  @Override
  public User findById(Long id) {
    return adapterOutRestUserMapper.map(userApi.findById(id));
  }
}
