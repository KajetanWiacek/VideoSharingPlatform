package com.kajetanwiacek.videosharingplatform.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
  @MockBean UserRepository userRepository;

  @MockBean PasswordEncoder passwordEncoder;

  @InjectMocks UserService userService;

  private User user;

  @BeforeEach
  public void startUp() {
    String password = passwordEncoder.encode("password");
    user = new User(1L, "example@gmail.com", password, "username");
  }

  @Test
  public void addUser_shouldAddUser_whenValidData() {
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
    User userToAdd = new User(1L, "example@gmail.com", "password", "username");

    userService.addUser(userToAdd);
    ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
    verify(userRepository).save(argumentCaptor.capture());
    User savedUser = argumentCaptor.getValue();

    Assertions.assertEquals(user, savedUser);
  }
}
