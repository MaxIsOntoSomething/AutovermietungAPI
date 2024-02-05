package com.SalerProjekt.services.auth;

import com.SalerProjekt.entity.User;
import com.SalerProjekt.enums.UserRole;
import com.SalerProjekt.dto.SignUpRequest;
import com.SalerProjekt.dto.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.SalerProjekt.repository.UserRepository;
import com.SalerProjekt.controller.AuthController;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findUserByRole(UserRole.ADMIN);
        if (adminAccount == null) {
            User  newAdminAccount = new User();
            newAdminAccount.setName("Admin");
            newAdminAccount.setEmail("admin@test.at");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            newAdminAccount.setRole(UserRole.ADMIN);
            userRepository.save(newAdminAccount);
            System.out.println("Created Admin Account");
        }
    }

    @Override
    public UserDto createCustomer(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithMail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

}


