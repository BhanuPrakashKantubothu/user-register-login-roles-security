package com.example.springSecurity6.controller;


import com.example.springSecurity6.dto.AuthResponseDto;
import com.example.springSecurity6.dto.LoginDto;
import com.example.springSecurity6.dto.RegisterRequestDto;
import com.example.springSecurity6.entity.User;
import com.example.springSecurity6.repository.UserRepository;
import com.example.springSecurity6.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {

        String token = authService.login(loginDto);


        AuthResponseDto authResponseDto = new AuthResponseDto(token);  // Pass token here

        return new ResponseEntity<>(authResponseDto,HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDto registerRequestDto) {
        if (userRepository.findByUsername(registerRequestDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setEmail(registerRequestDto.getEmail());
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }


}
