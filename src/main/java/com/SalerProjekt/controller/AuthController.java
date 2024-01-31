package com.SalerProjekt.controller;

import com.SalerProjekt.dto.AuthenticationRequest;
import com.SalerProjekt.dto.AuthenticationResponse;
import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import com.SalerProjekt.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import com.SalerProjekt.services.auth.AuthService;
import com.SalerProjekt.services.jwt.UserService;
import com.SalerProjekt.repository.UserRepository;
import com.SalerProjekt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@CrossOrigin
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;
    //@Autowired
    private final AuthenticationManager authenticationManager;
    //@Autowired
    private final UserService userService;
    //@Autowired
    private final JWTUtil jwtUtil;
    //@Autowired
    private final UserRepository userRepository;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> signUpCustomer(@RequestBody SignUpRequest signUpRequest) {
        if (authService.hasCustomerWithMail(signUpRequest.getEmail())) {
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

    @PostMapping(path = "/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws
            BadCredentialsException, DisabledException, UsernameNotFoundException{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserId(optionalUser.get().getId());
            authenticationResponse.setUserRole(optionalUser.get().getRole());
        }
        return authenticationResponse;
    }

}