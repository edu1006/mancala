package br.com.petrim.lich.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import br.com.petrim.lich.enums.RoleAccessEnum;
import br.com.petrim.lich.model.User;

public class CustomTokenConverter extends JwtAccessTokenConverter {

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {

        // FIXME: use to convert roles from secured

        OAuth2Authentication authentication = super.extractAuthentication(map);
        Authentication userAuthentication = authentication.getUserAuthentication();

        if (userAuthentication != null) {
            Object userId = map.get("userId");
            Object functionalities = map.get("f");

            User user = new User();
            user.setId(Long.valueOf(userId.toString()));

            AuthUserDetails userDetails = new AuthUserDetails(user, getAuthorities(functionalities));
            userAuthentication = new UsernamePasswordAuthenticationToken(userDetails,
                    userAuthentication.getCredentials(), userDetails.getAuthorities());
        }

        return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
    }
    
    @SuppressWarnings("unchecked")
	private List<AuthGrantAuthority> getAuthorities(Object oFunctionality) {
    	if (oFunctionality != null) {
    		return getAuthorities((List<Integer>) oFunctionality);
    	}
    	
    	return null;
    }
    
    private List<AuthGrantAuthority> getAuthorities(List<Integer> functionalities) {
    	List<AuthGrantAuthority> authorities = new ArrayList<>();
    	
    	AuthGrantAuthority authority = null;
    	for (Integer functionality : functionalities) {
    		Optional<RoleAccessEnum> optionalRole = RoleAccessEnum.valueOfFunctionaliry(functionality.longValue());
    		
    		if (optionalRole.isPresent()) {
    			authority = new AuthGrantAuthority(optionalRole.get().getRole());
    			authorities.add(authority);
    		}
    	}
    	
    	return authorities;
    }

}
