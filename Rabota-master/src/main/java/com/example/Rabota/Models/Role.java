package com.example.Rabota.Models;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MANAGER, KADROVIK;

    @Override
    public String getAuthority() {
        return name();
    }
}

