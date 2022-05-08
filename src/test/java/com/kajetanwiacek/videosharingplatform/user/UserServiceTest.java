package com.kajetanwiacek.videosharingplatform.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    private User user;
    @Before
    public void startUp(){
        String password = passwordEncoder.encode("password");
        user = new User(1L,"example@gmail.com",password,"username");
    }

    @Test
    public void addUser_shouldAddUser_whenValidData(){
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        User userToAdd = new User(1L,"example@gmail.com","password","username");

        userService.addUser(userToAdd);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());
        User savedUser = argumentCaptor.getValue();

        Assert.assertEquals(user,savedUser);
    }
}
