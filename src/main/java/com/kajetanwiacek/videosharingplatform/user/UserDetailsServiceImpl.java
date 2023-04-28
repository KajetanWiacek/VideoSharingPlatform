package com.kajetanwiacek.videosharingplatform.user;

import com.kajetanwiacek.videosharingplatform.exception.UserEmailNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl{
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
