package com.api.blog.services;

import com.api.blog.domain.dtos.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthenticationService {

    UserDetails authenticate(String email , String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);

    void register(RegisterRequest request);
}
