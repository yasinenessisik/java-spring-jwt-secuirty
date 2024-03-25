package com.javaspringjwt.javaspringjwt.service;


import com.javaspringjwt.javaspringjwt.dto.UserDto;
import com.javaspringjwt.javaspringjwt.exception.GenericException;
import com.javaspringjwt.javaspringjwt.model.User;
import com.javaspringjwt.javaspringjwt.repo.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserDto.builder()
                .username(user.getUsername())
                .userRole(String.valueOf(user.getUserRole()))
                .email(user.getEmail())
                .build();

    }
    public UserDto getUser(String username){
        User user = findUserByUsername(username);
        return UserDto.builder()
                .username(user.getUsername())
                .userRole(String.valueOf(user.getUserRole()))
                .email(user.getEmail())
                .build();
    }
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username).orElseThrow(() -> GenericException.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("User Not Found By Given Username.")
                .build());
    }



}
