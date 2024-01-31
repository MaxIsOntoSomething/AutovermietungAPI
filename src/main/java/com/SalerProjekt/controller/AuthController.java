package com.SalerProjekt.controller;

import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import lombok.RequiredArgsConstructor;
import com.SalerProjekt.services.auth.AuthService;
import com.SalerProjekt.repository.UserRepository;
import com.SalerProjekt.entity.User;
import com.SalerProjekt.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(path ="/sign-up")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest) {
        if(authService.hasCustomerWithMail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Customer with Email already exists", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdCustomerDto = authService.createCustomer(signUpRequest);
        if (createdCustomerDto == null) {
            return new ResponseEntity<>("Customer not created", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }
    @GetMapping(path = "/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test OK", HttpStatus.OK);
    }

}
