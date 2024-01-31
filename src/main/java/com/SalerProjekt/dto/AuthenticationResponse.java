package com.SalerProjekt.dto;

import com.SalerProjekt.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private UserRole userRole;
    private Long userId;

}
