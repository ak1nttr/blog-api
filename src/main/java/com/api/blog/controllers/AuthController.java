package com.api.blog.controllers;

import com.api.blog.domain.dtos.AuthResponse;
import com.api.blog.domain.dtos.LoginRequest;
import com.api.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /*
    *  any token generated expires in 24 hours
    * */
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        UserDetails userDetails = authService.authenticate(request.getEmail(), request.getPassword());
        String tokenGenerated = authService.generateToken(userDetails);
        System.out.println("got here!");
        AuthResponse response = AuthResponse.builder()
                .token(tokenGenerated)
                .expiresIn(86400)
                .build();
        logger.info("successful authentication for user: {}", request.getEmail());
        return ResponseEntity.ok(response);
        
    }


}
