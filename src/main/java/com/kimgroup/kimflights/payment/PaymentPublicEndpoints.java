package com.kimgroup.kimflights.payment;

import org.springframework.stereotype.Component;

import com.kimgroup.kimflights.security.configuration.PublicEndpointProvider;

@Component
public class PaymentPublicEndpoints implements PublicEndpointProvider {
    @Override
    public String[] getPublicEndpoints() {
        return new String[] {};
    }
}
