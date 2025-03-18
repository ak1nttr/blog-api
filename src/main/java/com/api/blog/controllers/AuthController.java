package com.api.blog.controllers;

import com.api.blog.domain.dtos.AuthResponse;
import com.api.blog.domain.dtos.LoginRequest;
import com.api.blog.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

        try {
            UserDetails userDetails = authService.authenticate(request.getEmail(), request.getPassword());
            String tokenGenerated = authService.generateToken(userDetails);
            System.out.println("got here!");
            AuthResponse response = AuthResponse.builder()
                    .token(tokenGenerated)
                    .expiresIn(86400)
                    .build();
            logger.info("successful authentication for user: {}",request.getEmail());
            return ResponseEntity.ok(response);
        }catch (BadCredentialsException bc) {
            logger.warn("failed to authenticate for user: {}",request.getEmail());
            logger.error("error message: "+bc.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            logger.error("authentication error: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }


}
