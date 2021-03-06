package de.oose;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.client.RestTemplate;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	public SecurityConfig() {
        //to use principal and authentication together with @async
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
	
	/**
	 * Registers the KeycloakAuthenticationProvider with the authentication manager.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		var keyCloakAuthProvider = keycloakAuthenticationProvider();
		keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keyCloakAuthProvider);
	}
	
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}
	

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		super.configure(http);
		http
			.csrf().disable()
			.authorizeRequests().anyRequest().authenticated();
	}
		
	@Bean
	public KeycloakConfigResolver keyCloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
	@Bean
	public RestTemplate restTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory) {
		return new KeycloakRestTemplate(keycloakClientRequestFactory);
	}
}