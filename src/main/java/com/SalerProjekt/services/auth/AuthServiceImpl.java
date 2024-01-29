package com.SalerProjekt.services.auth;

import com.SalerProjekt.entity.User;
import com.SalerProjekt.enums.UserRole;
import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.SalerProjekt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @Override
    public UserDto createCustomer(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }
}


