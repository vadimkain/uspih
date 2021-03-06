package com.example.uspih.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ACTIVATE;

    @Override
    public String getAuthority() {
        return name();
    }
}