package br.com.petrim.lich.security;

import br.com.petrim.lich.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {

        // FIXME: use to convert roles from secured

        OAuth2Authentication authentication = super.extractAuthentication(map);
        Authentication userAuthentication = authentication.getUserAuthentication();

        if (userAuthentication != null) {
            Object userId = map.get("userId");

            User user = new User();
            user.setId(Long.valueOf(userId.toString()));

            AuthUserDetails userDetails = new AuthUserDetails(user);
            userAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                    userAuthentication.getCredentials(), userDetails.getAuthorities());
        }

        return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
    }

}
