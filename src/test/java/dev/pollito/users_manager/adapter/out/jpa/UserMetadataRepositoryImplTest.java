package dev.pollito.users_manager.adapter.out.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.adapter.out.jpa.entity.UserMetadataJpaEntity;
import dev.pollito.users_manager.adapter.out.jpa.repository.UserMetadataJpaRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserMetadataRepositoryImplTest {
  @InjectMocks private UserMetadataRepositoryImpl userMetadataRepository;
  @Mock private UserMetadataJpaRepository userMetadataJpaRepository;

  @Test
  void shouldReturnMap_whenFindProfilePictureUrlByIds() {
    UserMetadataJpaEntity entity1 = new UserMetadataJpaEntity();
    entity1.setUserId(1L);
    entity1.setProfilePictureUrl("url1");

    when(userMetadataJpaRepository.findByUserIdIn(anyList())).thenReturn(List.of(entity1));

    Map<Long, Optional<String>> result =
        userMetadataRepository.findProfilePictureUrlByIds(List.of(1L, 2L));
    assertEquals(2, result.size());
    assertEquals(Optional.of("url1"), result.get(1L));
    assertEquals(Optional.empty(), result.get(2L));
  }
}
