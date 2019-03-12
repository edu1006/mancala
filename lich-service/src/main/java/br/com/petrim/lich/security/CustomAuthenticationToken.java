package br.com.petrim.lich.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Long userId;

    public CustomAuthenticationToken(Long userId, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.userId = userId;
    }

    public CustomAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public Long getUserId() {
        return userId;
    }
}
