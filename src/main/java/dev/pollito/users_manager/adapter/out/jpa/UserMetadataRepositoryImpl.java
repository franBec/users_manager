package dev.pollito.users_manager.adapter.out.jpa;

import static java.util.Optional.ofNullable;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toMap;

import dev.pollito.users_manager.adapter.out.jpa.entity.UserMetadataJpaEntity;
import dev.pollito.users_manager.adapter.out.jpa.repository.UserMetadataJpaRepository;
import dev.pollito.users_manager.domain.port.out.UserMetadataRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMetadataRepositoryImpl implements UserMetadataRepository {
  private final UserMetadataJpaRepository userMetadataJpaRepository;

  @Override
  public Map<Long, Optional<String>> findProfilePictureUrlByIds(List<Long> ids) {
    Map<Long, UserMetadataJpaEntity> entityMap =
        userMetadataJpaRepository.findByUserIdIn(ids).stream()
            .collect(toMap(UserMetadataJpaEntity::getUserId, identity()));

    return ids.stream()
        .collect(
            toMap(
                id -> id,
                id ->
                    ofNullable(entityMap.get(id))
                        .map(UserMetadataJpaEntity::getProfilePictureUrl)));
  }
}
