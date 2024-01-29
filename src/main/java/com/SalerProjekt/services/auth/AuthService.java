package com.SalerProjekt.services.auth;

import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;

public interface AuthService {
    UserDto createCustomer(SignUpRequest signUpRequest);

    boolean hasCustomerWithMail(String email);

}
