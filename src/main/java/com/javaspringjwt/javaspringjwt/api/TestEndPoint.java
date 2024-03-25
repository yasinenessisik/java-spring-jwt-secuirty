package com.javaspringjwt.javaspringjwt.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
public class TestEndPoint {


    @PostMapping("/admin")
    public String admin(){
        return "admin";
    }
    @PostMapping("/user")
    public String user(){
        return "user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/public")
    public String publicendpoint(){
        return "public";
    }
}
