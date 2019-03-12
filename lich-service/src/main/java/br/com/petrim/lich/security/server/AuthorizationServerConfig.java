package br.com.petrim.lich.security.server;

import br.com.petrim.lich.security.AuthUserDetailsService;
import br.com.petrim.lich.security.CustomTokenConverter;
import br.com.petrim.lich.security.CustomTokenEnhancer;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(SpringContextUtil.getBean(JwtAccessTokenConverter.class));
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        CustomTokenConverter accessTokenConverter = new CustomTokenConverter();
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setSupportRefreshToken(Boolean.TRUE);
        tokenServices.setTokenStore(SpringContextUtil.getBean(TokenStore.class));
        tokenServices.setTokenEnhancer(tokenEnhancer());

        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(),
                SpringContextUtil.getBean(JwtAccessTokenConverter.class)));

        endpoints
                .tokenStore(SpringContextUtil.getBean(TokenStore.class))
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.authUserDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.OPTIONS);

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients
                .inMemory()
                .withClient("front-web")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .scopes("bar", "read", "write")
                .refreshTokenValiditySeconds(6000)
                .accessTokenValiditySeconds(1800)
                .secret("{noop}1234")
                .resourceIds("front-web-services");
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

}
