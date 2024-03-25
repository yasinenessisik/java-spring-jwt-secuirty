package com.javaspringjwt.javaspringjwt.service;


import com.javaspringjwt.javaspringjwt.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    final private UserServiceImp userServiceImp;

    public UserDetailsServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImp.findUserByUsername(username);
        List<SimpleGrantedAuthority> roles = Stream.of(user.getUserRole())
                .map(x -> new SimpleGrantedAuthority(x.name()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),roles);
    }
}
