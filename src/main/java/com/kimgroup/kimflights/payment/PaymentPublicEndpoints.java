package com.kimgroup.kimflights.payment;

import com.kimgroup.kimflights.security.configuration.PublicEndpointProvider;
import org.springframework.stereotype.Component;

@Component
public class PaymentPublicEndpoints implements PublicEndpointProvider {
    @Override
    public String[] getPublicEndpoints() {
        return new String[]{"/payment/brand/**"};
    }
}
