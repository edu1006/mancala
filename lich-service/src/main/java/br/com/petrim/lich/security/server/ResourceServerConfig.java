package br.com.petrim.lich.security.server;

import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());
        resources.resourceId("front-web-services");
    }

    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(SpringContextUtil.getBean(TokenStore.class));
        return tokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .logout()
                .invalidateHttpSession(Boolean.TRUE)
                .clearAuthentication(Boolean.TRUE)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/**").permitAll();
//                .anyRequest().denyAll();
    }
}
