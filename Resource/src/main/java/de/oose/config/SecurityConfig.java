package de.oose.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.zalando.logbook.HeaderFilter;
import org.zalando.logbook.Logbook;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{//KeycloakWebSecurityConfigurerAdapter {

	/**
	 * Registers the KeycloakAuthenticationProvider with the authentication manager.
	 *//*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		var keyCloakAuthProvider = keycloakAuthenticationProvider();
		keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
		auth.authenticationProvider(keyCloakAuthProvider);
	}
*/
	
	/**
	 * Defines the session authentication strategy.
	 */
	/*
	@Bean
	@Override
	protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new NullAuthenticatedSessionStrategy();
	}
	*/
	

	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests().anyRequest().authenticated();
	//		.authorizeRequests().anyRequest().authenticated()
			//.and().requiresChannel().anyRequest().requiresSecure()
			//.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	}
	
	
	/*
	@Bean
	public KeycloakConfigResolver keyCloakConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	*/


}