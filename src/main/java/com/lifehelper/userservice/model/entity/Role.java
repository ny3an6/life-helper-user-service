package com.lifehelper.userservice.model.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    USER("USER");

    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
