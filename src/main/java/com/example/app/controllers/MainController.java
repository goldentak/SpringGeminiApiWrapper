package com.example.app.controllers;

import com.example.app.services.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.app.services.UserService;
import com.example.app.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/auth")
public class MainController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public MainController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {

        userService.register(userDto.getUsername(), userDto.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {

        boolean success = userService.login(userDto.getUsername(), userDto.getPassword());
        if (success) {
            String token = jwtUtil.generateToken(userDto.getUsername());
            return ResponseEntity.ok("Welcome " + userDto.getUsername() + "!" + "your" + " token is " + token);
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }



    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String token = extractTokenFromRequest(request);

        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
        }

        String username = jwtUtil.extractUsername(token);
        return ResponseEntity.ok("Welcome, " + username + "! This is your profile.");

    }
    private String extractTokenFromRequest(HttpServletRequest request){
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.startsWith("Bearer ")){
            return header.substring(7);
        }
        return null;
    }
}
