package com.example.springSecurity6.service;


import com.example.springSecurity6.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(LoginDto loginDto);
}
