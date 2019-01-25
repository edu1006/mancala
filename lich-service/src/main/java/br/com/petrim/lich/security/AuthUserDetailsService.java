package br.com.petrim.lich.security;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optional = userService.findByLogin(s);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new AuthUserDetails(optional.get());
    }
}
