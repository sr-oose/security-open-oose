package de.oose.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		var keyCloakAuthProvider = keycloakAuthenticationProvider();
		keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keyCloakAuthProvider);
	}*/

	/**
	 * Defines the session authentication strategy.
	 */
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		 return new RegisterSessionAuthenticationStrategy(
		          new SessionRegistryImpl());
//		return new NullAuthenticatedSessionStrategy();
	}


	@Override
	public void configure(final HttpSecurity http) throws Exception {
		super.configure(http);
		http
			.csrf().disable()
/*			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.sessionAuthenticationStrategy(sessionAuthenticationStrategy())*/
			.authorizeRequests().antMatchers("/api/**").authenticated()
								.antMatchers("/echo/**").authenticated()
								.anyRequest().permitAll();
			//.and().requiresChannel().anyRequest().requiresSecure();
	}
	
    /**
     * Use properties in application.properties instead of keycloak.json
     */
	@Bean
	public KeycloakConfigResolver keyCloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}

	@Bean
	public FilterRegistrationBean<KeycloakAuthenticationProcessingFilter> keycloakAuthenticationProcessingFilterRegistrationBean(
			KeycloakAuthenticationProcessingFilter filter) {
		FilterRegistrationBean<KeycloakAuthenticationProcessingFilter> registrationBean = new FilterRegistrationBean<KeycloakAuthenticationProcessingFilter>(
				filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<KeycloakPreAuthActionsFilter> keycloakPreAuthActionsFilterRegistrationBean(
			KeycloakPreAuthActionsFilter filter) {
		FilterRegistrationBean<KeycloakPreAuthActionsFilter> registrationBean = new FilterRegistrationBean<KeycloakPreAuthActionsFilter>(
				filter);
		registrationBean.setEnabled(false);
		return registrationBean;
	}
	
	@Bean
	public KeycloakZuulFilter keycloakZuulFilter() {
		return new KeycloakZuulFilter();
	}


}