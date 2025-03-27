package dev.pollito.users_manager.repository;

import dev.pollito.users_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
      "SELECT u FROM User u "
          + "WHERE (:q IS NULL OR "
          + "LOWER(u.name) LIKE LOWER(CONCAT('%', :q, '%')) OR "
          + "LOWER(u.username) LIKE LOWER(CONCAT('%', :q, '%')) OR "
          + "LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%')))")
  Page<User> findAllByQueryContainingIgnoreCase(Pageable pageable, @Param("q") String q);
}
