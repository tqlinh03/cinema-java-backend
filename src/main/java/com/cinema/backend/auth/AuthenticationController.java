package com.cinema.backend.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.backend.auth.dto.AuthenticationResponse;
import com.cinema.backend.auth.dto.RegistrationRequest;
import com.cinema.backend.modal.user.dto.UserResponse;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> register(
            @RequestBody @Valid RegistrationRequest request) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().body("Ok.");
    
    }
  
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(service.authenticate(request, response));
    }

    @GetMapping("/activate-account")
    public ResponseEntity<String> confirm(
            @RequestParam String token) throws MessagingException {
        service.activateAccount(token);
        return ResponseEntity.accepted().body("Ok.");
    }

    @PostMapping("/sendValidationEmail")
    public ResponseEntity<String> sendValidationEmail(
        @RequestParam String email) throws MessagingException {
        service.sendCodeEmail(email);
        return ResponseEntity.accepted().body("Đã gửi mã kích hoạt tài khoản.");
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/account")
    public ResponseEntity<UserResponse> fetchAccount(  
        @RequestParam String accessToken) throws MessagingException{
        return ResponseEntity.ok(service.fetchAccount(accessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletResponse response) {
        service.logout(response);
        return ResponseEntity.accepted().body("Ok.");
    }
    

}
