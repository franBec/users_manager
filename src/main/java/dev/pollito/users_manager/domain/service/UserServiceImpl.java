package dev.pollito.users_manager.domain.service;

import dev.pollito.users_manager.domain.model.User;
import dev.pollito.users_manager.domain.port.in.UserService;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private static final List<User> USERS =
      List.of(
          User.builder()
              .id(1L)
              .name("Leanne Graham")
              .username("Bret")
              .email("Sincere@april.biz")
              .build());

  @Override
  public Page<User> getUsers(@NotNull Pageable pageable) {
    List<User> sortedUsers = sortUsers(USERS, pageable.getSort());

    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<User> pageContent;

    if (startItem >= sortedUsers.size()) {
      pageContent = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, sortedUsers.size());
      pageContent = sortedUsers.subList(startItem, toIndex);
    }

    return new PageImpl<>(pageContent, pageable, sortedUsers.size());
  }

  @Override
  public User getUserById(Long id) {
    return null;
  }

  private List<User> sortUsers(List<User> users, @NotNull Sort sort) {
    if (sort.isUnsorted()) {
      return users;
    }

    List<User> sortedList = new ArrayList<>(users);

    Comparator<User> comparator = null;
    for (Sort.Order order : sort) {
      Comparator<User> currentComparator = switch (order.getProperty().toLowerCase()) {
        case "id" -> Comparator.comparing(User::getId);
        case "name" -> Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER);
        case "username" -> Comparator.comparing(User::getUsername, String.CASE_INSENSITIVE_ORDER);
        case "email" -> Comparator.comparing(User::getEmail, String.CASE_INSENSITIVE_ORDER);
        default -> null;
      };

      if (currentComparator != null) {
        if (order.isDescending()) {
          currentComparator = currentComparator.reversed();
        }
        comparator = (comparator == null) ? currentComparator : comparator.thenComparing(currentComparator);
      }
    }

      sortedList.sort(Objects.requireNonNullElseGet(comparator, () -> Comparator.comparing(User::getId)));

    return sortedList;
  }
}
