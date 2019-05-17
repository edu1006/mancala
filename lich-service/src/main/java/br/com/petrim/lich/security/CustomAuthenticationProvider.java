package br.com.petrim.lich.security;

import br.com.petrim.lich.util.HashUtil;
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
            AuthUserDetails authUserDetails = (AuthUserDetails) userDetails;

            String hashPassword = HashUtil.hashHexa(String.valueOf(authentication.getCredentials()), "SHA-256");
            if (!userDetails.getPassword().equals(hashPassword)) {
                throw new BadCredentialsException("Bad credentials");
            }

            return new CustomAuthenticationToken(authUserDetails.getUser().getId(), authUserDetails.getUsername(),
                    authUserDetails.getPassword(), authUserDetails.getAuthorities());

        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Bad credentials");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
