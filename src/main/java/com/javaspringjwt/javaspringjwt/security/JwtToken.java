package com.javaspringjwt.javaspringjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaspringjwt.javaspringjwt.service.UserDetailsServiceImp;
import com.javaspringjwt.javaspringjwt.service.UserServiceImp;
import com.javaspringjwt.javaspringjwt.utils.TokenGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtToken extends OncePerRequestFilter {
    private final TokenGenerator tokenGenerator;
    private final UserDetailsServiceImp userDetailsServiceImp;

    public JwtToken(TokenGenerator tokenGenerator, UserServiceImp userServiceImp, UserDetailsServiceImp userDetailsServiceImp) {
        this.tokenGenerator = tokenGenerator;
        this.userDetailsServiceImp = userDetailsServiceImp;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        String username;
        try{
            if (!token.isBlank()){
                username = tokenGenerator.verifyToken(token).getSubject();
                UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request,response);
        }catch (Exception e){
            response.setContentType("application/json");
            Map<String,String> error = new HashMap<>();
            error.put("error",e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper objectMapper = new ObjectMapper();
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }
    }

    private String getToken(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")){
            return "";
        }
        return header.substring(7);
    }
}
