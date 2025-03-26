package dev.pollito.users_manager.service.impl;

import static dev.pollito.users_manager.MockData.USERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import dev.pollito.users_manager.mapper.UserMapper;
import dev.pollito.users_manager.model.Users;
import dev.pollito.users_manager.service.UserApiCacheService;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @InjectMocks private UserServiceImpl userService;
  @Mock private UserApiCacheService userApiCacheService;

  @SuppressWarnings("unused")
  @Spy
  private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

  @BeforeEach
  void setUp() {
    when(userApiCacheService.getUsers()).thenReturn(USERS);
  }

  @Test()
  void whenGetUsersThenReturnUserList() {
    Users response = userService.findAll(0, 10, null, null);
    assertEquals(10, response.getTotalElements());
    assertNotNull(response.getPageable());
    assertEquals(0, response.getPageable().getPageNumber());
    assertEquals(10, response.getPageable().getPageSize());
    assertEquals(1, response.getContent().getFirst().getId());
    assertEquals(10, response.getContent().getLast().getId());
  }

  @Test()
  void whenGetUsersDescThenReturnUserListDesc() {
    Users response = userService.findAll(0, 10, List.of("id:desc"), null);
    assertEquals(10, response.getContent().getFirst().getId());
    assertEquals(1, response.getContent().getLast().getId());
  }

  @Test
  void whenGetUsersWithQThenReturnSubList() {
    Users lePage0 = userService.findAll(0, 5, null, "le");
    assertEquals(5, lePage0.getContent().size());
    assertEquals(7, lePage0.getTotalElements());

    Users lePage1 = userService.findAll(1, 5, null, "le");
    assertEquals(2, lePage1.getContent().size());
    assertEquals(7, lePage1.getTotalElements());

    Users biz = userService.findAll(0, 10, null, "biz");
    assertEquals(3, biz.getContent().size());
    assertEquals(3, biz.getTotalElements());
  }

  @Test
  void whenSortByPropertyThenReturnSortedList() {
    Users sortByEmail = userService.findAll(0, 10, List.of("email"), null);
    assertEquals("Chaim_McDermott@dana.io", sortByEmail.getContent().getFirst().getEmail());
    assertEquals("Telly.Hoeger@billy.biz", sortByEmail.getContent().getLast().getEmail());

    Users sortByName = userService.findAll(0, 10, List.of("name"), null);
    assertEquals("Chelsey Dietrich", sortByName.getContent().getFirst().getName());
    assertEquals("Patricia Lebsack", sortByName.getContent().getLast().getName());

    Users sortByUsername = userService.findAll(0, 10, List.of("username"), null);
    assertEquals("Antonette", sortByUsername.getContent().getFirst().getUsername());
    assertEquals("Samantha", sortByUsername.getContent().getLast().getUsername());

    Users sortBySamePropertyTwice = userService.findAll(0,10,List.of("id:asc","id:asc"),null);
    assertEquals(1, sortBySamePropertyTwice.getContent().getFirst().getId());
    assertEquals(10, sortBySamePropertyTwice.getContent().getLast().getId());
  }

  @Test
  void whenGetUserThenReturnUser() {
    assertNotNull(userService.findById(1L));
  }

  @Test
  void whenGetUserThatDoesntExistThenThrowException() {
    assertThrows(NoSuchElementException.class, () -> userService.findById(-1L));
  }
}
