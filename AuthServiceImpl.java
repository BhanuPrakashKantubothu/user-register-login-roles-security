package com.example.springSecurity6.service;


import com.example.springSecurity6.dto.LoginDto;

import com.example.springSecurity6.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =
                authenticationManager.
                        authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtTokenProvider.generateToken(authentication);
        return token;
    }
}
