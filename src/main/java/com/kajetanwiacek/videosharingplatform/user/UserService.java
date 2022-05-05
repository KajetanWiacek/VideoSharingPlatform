package com.kajetanwiacek.videosharingplatform.user;

import com.kajetanwiacek.videosharingplatform.exception.EmailAlreadyTakenException;
import com.kajetanwiacek.videosharingplatform.exception.InvalidEmailException;
import com.kajetanwiacek.videosharingplatform.exception.UserEmailNotFoundException;
import com.kajetanwiacek.videosharingplatform.exception.UserNotFoundException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyTakenException(user.getEmail());
        }
        if(!EmailValidator.getInstance().isValid(user.getEmail()))
        {
            throw new InvalidEmailException();
        }
        user.encodePassword(passwordEncoder);
        userRepository.save(user);
    }

    public User getUser(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }
}
