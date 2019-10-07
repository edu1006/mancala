package br.com.petrim.lich.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.petrim.lich.model.User;
import br.com.petrim.lich.repository.UserRepository;
import br.com.petrim.lich.util.SpringContextUtil;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        String login = String.valueOf(oAuth2Authentication.getPrincipal());
        UserRepository userRepository = SpringContextUtil.getBean(UserRepository.class);

        Optional<User> optional = userRepository.findByLogin(login);

        if (optional.isPresent()) {

            User user = optional.get();
            
            List<Long> functionalities = userRepository.findFunctionalityByUser(user.getId());

            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("userId", user.getId());
            additionalInfo.put("f", functionalities);

            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);

            return oAuth2AccessToken;
        }

        throw new RuntimeException("User not found!");
    }

}
