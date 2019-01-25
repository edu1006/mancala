package br.com.petrim.lich.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            UserDetails userDetails = authUserDetailsService.loadUserByUsername(String.valueOf(authentication.getPrincipal()));

            if (!userDetails.getPassword().equals(String.valueOf(authentication.getCredentials()))) {
                throw new BadCredentialsException("Bad credentials");
            } else {
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), userDetails.getAuthorities());
            }
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Bad credentials");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
