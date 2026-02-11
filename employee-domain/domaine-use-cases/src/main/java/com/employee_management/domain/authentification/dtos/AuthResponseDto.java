package com.employee_management.domain.authentification.dtos;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthResponseDto {
    private String accessToken;
    private String tokenType;
    private Long userId;
    private String role;
}