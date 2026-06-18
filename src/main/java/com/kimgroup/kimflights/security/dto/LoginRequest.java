package com.kimgroup.kimflights.security.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
