package com.kajetanwiacek.videosharingplatform.user;

import com.kajetanwiacek.videosharingplatform.exception.EmailAlreadyTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        user.encodePassword(passwordEncoder);
        userRepository.save(user);
    }

    public User getUser(String email){
        return userRepository.findByEmail(email);
    }
}
