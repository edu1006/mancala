package br.com.petrim.lich.resources;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.security.AuthUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public abstract class AbstractResource {

    protected User getCurrentUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

        return userDetails.getUser();
    }

}
