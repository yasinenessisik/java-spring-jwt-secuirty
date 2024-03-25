package com.javaspringjwt.javaspringjwt.config;

import com.javaspringjwt.javaspringjwt.model.User;
import com.javaspringjwt.javaspringjwt.model.UserRole;
import com.javaspringjwt.javaspringjwt.service.UserServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {
    public final UserServiceImp userServiceImp;

    public StartupConfig(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public void run(String... args) throws Exception {
        userServiceImp.createUser(User.builder()
                        .username("enes")
                        .password("pass")
                        .userRole(UserRole.ADMIN)
                .build());
        userServiceImp.createUser(User.builder()
                .username("furkan")
                .password("pass")
                .userRole(UserRole.USER)
                .build());
    }
}
