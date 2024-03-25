package com.javaspringjwt.javaspringjwt.service;


import com.javaspringjwt.javaspringjwt.dto.LoginRequest;
import com.javaspringjwt.javaspringjwt.dto.TokenResponseDto;
import com.javaspringjwt.javaspringjwt.exception.GenericException;
import com.javaspringjwt.javaspringjwt.utils.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp {
    private final UserServiceImp userServiceImp;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImp(UserServiceImp userServiceImp, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.userServiceImp = userServiceImp;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginRequest loginRequest) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(authentication))
                    .userDto(userServiceImp.getUser(loginRequest.getUsername()))
                    .build();
        }catch (Exception e){
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
    public String getLoggedInUsername(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
