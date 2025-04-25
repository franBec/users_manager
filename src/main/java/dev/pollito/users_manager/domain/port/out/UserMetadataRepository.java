package dev.pollito.users_manager.domain.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserMetadataRepository {
  Map<Long, Optional<String>> findProfilePictureUrlByIds(List<Long> ids);
}
