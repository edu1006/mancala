package br.com.petrim.lich.security;

import org.springframework.security.core.GrantedAuthority;

public class AuthGrantAuthority implements GrantedAuthority {

    private String authority;

    public AuthGrantAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
