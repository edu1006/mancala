package br.com.petrim.lich.listener;

import br.com.petrim.lich.security.AuthUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import java.util.Optional;

public class AuditEntity implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
//        return Optional.of(getAuthUser().getId());

        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();

        return Optional.of(userDetails.getUser().getId());
    }
}
