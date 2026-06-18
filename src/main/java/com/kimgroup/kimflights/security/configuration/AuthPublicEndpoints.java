package com.kimgroup.kimflights.security.configuration;

import org.springframework.stereotype.Component;

@Component
public class AuthPublicEndpoints implements PublicEndpointProvider {
    @Override
    public String[] getPublicEndpoints() {
        return new String[]{"/api/auth/**"};
    }
}
