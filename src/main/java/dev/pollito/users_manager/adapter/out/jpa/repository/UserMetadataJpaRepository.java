package dev.pollito.users_manager.adapter.out.jpa.repository;

import dev.pollito.users_manager.adapter.out.jpa.entity.UserMetadataJpaEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetadataJpaRepository extends JpaRepository<UserMetadataJpaEntity, Long> {
  List<UserMetadataJpaEntity> findByUserIdIn(List<Long> userIds);
}
