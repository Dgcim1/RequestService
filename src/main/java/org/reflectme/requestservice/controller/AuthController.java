package org.reflectme.requestservice.controller;

import lombok.AllArgsConstructor;
import org.reflectme.requestservice.security.AuthenticationService;
import org.reflectme.requestservice.util.AuthenticationRequest;
import org.reflectme.requestservice.util.AuthenticationResponse;
import org.reflectme.requestservice.util.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final org.reflectme.requestservice.security.config.JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> auth(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> token(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(jwtService.generateToken(
                new UserData(
                        request.getName(),
                        request.getPassword()
                )
        ));
    }
}
