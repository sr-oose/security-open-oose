package de.oose;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	   @Bean
	   public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	      http.authorizeExchange(exchanges -> exchanges
	    		  .pathMatchers("/api/**").authenticated()
	    		  .pathMatchers("/echo/**").permitAll()
	    		  .anyExchange().authenticated())
	           .oauth2Login()
	          .and().redirectToHttps()
	    	  .and().csrf().disable();
	      return http.build();
	   }

}