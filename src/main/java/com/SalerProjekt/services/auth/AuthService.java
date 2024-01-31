package com.SalerProjekt.services.auth;

import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import com.SalerProjekt.entity.User;
import com.SalerProjekt.repository.UserRepository;
import com.SalerProjekt.controller.AuthController;
public interface AuthService {
    UserDto createCustomer(SignUpRequest signUpRequest);

    boolean hasCustomerWithMail(String email);
}
