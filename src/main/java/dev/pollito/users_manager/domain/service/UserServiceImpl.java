package dev.pollito.users_manager.domain.service;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.in.UserService;
import dev.pollito.users_manager.domain.port.out.UserApiClient;
import dev.pollito.users_manager.domain.port.out.UserMetadataRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserApiClient userApiClient;
  private final UserMetadataRepository userMetadataRepository;

  @Override
  public List<User> findAll() {
    List<User> users = userApiClient.findAll();
    Map<Long, Optional<String>> profilePictureUrls =
        userMetadataRepository.findProfilePictureUrlByIds(users.stream().map(User::getId).toList());
    users.forEach(
        user -> profilePictureUrls.get(user.getId()).ifPresent(user::setProfilePictureUrl));
    return users;
  }

  @Override
  public User findById(Long id) {
    User user = userApiClient.findById(id).orElseThrow();
    userMetadataRepository
        .findProfilePictureUrlByIds(List.of(id))
        .get(id)
        .ifPresent(user::setProfilePictureUrl);
    return user;
  }
}
