package com.kimgroup.kimflights.security.configuration;

import org.springframework.modulith.NamedInterface;

@NamedInterface
public interface PublicEndpointProvider {
    String[] getPublicEndpoints();
}
