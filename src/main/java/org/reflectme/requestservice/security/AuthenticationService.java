package org.reflectme.requestservice.security;

import lombok.AllArgsConstructor;

import org.reflectme.requestservice.util.AuthenticationRequest;
import org.reflectme.requestservice.util.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserDetailsService envService;
    private final AuthenticationManager authenticationManager;
    private final org.reflectme.requestservice.security.config.JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        var user = envService.loadUserByUsername(request.getName());
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

}
