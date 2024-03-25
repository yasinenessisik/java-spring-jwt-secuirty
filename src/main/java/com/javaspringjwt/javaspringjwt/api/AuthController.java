package com.javaspringjwt.javaspringjwt.api;


import com.javaspringjwt.javaspringjwt.dto.LoginRequest;
import com.javaspringjwt.javaspringjwt.dto.TokenResponseDto;
import com.javaspringjwt.javaspringjwt.service.AuthServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImp authServiceImp;

    public AuthController(AuthServiceImp authServiceImp) {
        this.authServiceImp = authServiceImp;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest){
        System.out.println("methoda istek geldi");
        return ResponseEntity.ok(authServiceImp.login(loginRequest));
    }

}
