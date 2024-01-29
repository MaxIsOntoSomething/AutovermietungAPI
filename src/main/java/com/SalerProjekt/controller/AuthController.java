package com.SalerProjekt.controller;

import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import lombok.RequiredArgsConstructor;
import com.SalerProjekt.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public abstract class AuthController {
    private final AuthService authService;

    /*
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    */
    @PostMapping("/signup")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest) {
        UserDto createdCustomerDto = authService.createCustomer(signUpRequest);
        if (createdCustomerDto == null) {
            return new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>("Test OK", HttpStatus.OK);
    }
}
