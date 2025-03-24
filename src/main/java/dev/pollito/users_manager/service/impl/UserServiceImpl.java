package dev.pollito.users_manager.service.impl;

import dev.pollito.users_manager.mapper.UserMapper;
import dev.pollito.users_manager.model.Pageable;
import dev.pollito.users_manager.model.User;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserApiCacheService;
import dev.pollito.users_manager.service.UserService;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserApiCacheService userApi;
  private final UserMapper userMapper;

  @Override
  public Users findAll(Integer pageNumber, Integer pageSize, List<String> pageSort, String q) {
    List<com.typicode.jsonplaceholder.model.User> users = userApi.getUsers();

    return new Users()
        .content(
            userMapper.map(
                applyPagination(
                    sortUsers(filterUsersByQ(users, q), pageSort), pageNumber, pageSize)))
        .pageable(new Pageable().pageNumber(pageNumber).pageSize(pageSize))
        .totalElements(users.size());
  }

  @Override
  public User findById(Long id) {
    return userMapper.map(
        userApi.getUsers().stream()
            .filter(u -> Objects.nonNull(u.getId()))
            .filter(u -> u.getId() == id.intValue())
            .findFirst()
            .orElseThrow());
  }

  private static List<com.typicode.jsonplaceholder.model.User> filterUsersByQ(
      @NotNull List<com.typicode.jsonplaceholder.model.User> users, String q) {
    if (StringUtils.isBlank(q)) {
      return users;
    }
    return new ArrayList<>(users)
        .stream().filter(user -> filterUsersByQPredicate(user, q)).toList();
  }

  private static boolean filterUsersByQPredicate(
      com.typicode.jsonplaceholder.model.@NotNull User user, @NotNull String q) {
    String query = q.toLowerCase();
    return (Objects.nonNull(user.getEmail()) && user.getEmail().toLowerCase().contains(query))
        || (Objects.nonNull(user.getName()) && user.getName().toLowerCase().contains(query))
        || (Objects.nonNull(user.getUsername())
            && user.getUsername().toLowerCase().contains(query));
  }

  private static @NotNull List<com.typicode.jsonplaceholder.model.User> sortUsers(
      @NotNull List<com.typicode.jsonplaceholder.model.User> users, List<String> pageSort) {

    if (users.isEmpty()) {
      return Collections.emptyList();
    }

    List<com.typicode.jsonplaceholder.model.User> result = new ArrayList<>(users);

    List<String> criteria =
        (Objects.isNull(pageSort) || pageSort.isEmpty())
            ? Collections.singletonList("id:asc")
            : pageSort;

    Comparator<com.typicode.jsonplaceholder.model.User> comparator = null;

    for (String sortStr : criteria) {
      String[] parts = sortStr.split(":", 2);
      String field = parts[0].trim().toLowerCase();
      Comparator<com.typicode.jsonplaceholder.model.User> current = getUserComparator(parts, field);

      if (Objects.isNull(comparator)) {
        comparator = current;
      } else {
        comparator = comparator.thenComparing(current);
      }
    }

    result.sort(comparator);
    return result;
  }

  private static @NotNull Comparator<com.typicode.jsonplaceholder.model.User> getUserComparator(
      String @NotNull [] parts, @NotNull String field) {
    String dir = (parts.length > 1) ? parts[1].trim().toLowerCase() : "asc";

    Comparator<com.typicode.jsonplaceholder.model.User> current =
        switch (field) {
          case "name" ->
              Comparator.comparing(
                  com.typicode.jsonplaceholder.model.User::getName,
                  Comparator.nullsFirst(Comparator.naturalOrder()));
          case "username" ->
              Comparator.comparing(
                  com.typicode.jsonplaceholder.model.User::getUsername,
                  Comparator.nullsFirst(Comparator.naturalOrder()));
          case "email" ->
              Comparator.comparing(
                  com.typicode.jsonplaceholder.model.User::getEmail,
                  Comparator.nullsFirst(Comparator.naturalOrder()));
          default ->
              Comparator.comparing(
                  com.typicode.jsonplaceholder.model.User::getId,
                  Comparator.nullsFirst(Comparator.naturalOrder()));
        };

    if ("desc".equals(dir)) {
      current = current.reversed();
    }
    return current;
  }

  private @NotNull List<com.typicode.jsonplaceholder.model.User> applyPagination(
      @NotNull List<com.typicode.jsonplaceholder.model.User> users,
      Integer pageNumber,
      Integer pageSize) {
    int total = users.size();
    int fromIndex = Math.min(pageNumber * pageSize, total);
    int toIndex = Math.min(fromIndex + pageSize, total);

    return users.subList(fromIndex, toIndex);
  }
}
