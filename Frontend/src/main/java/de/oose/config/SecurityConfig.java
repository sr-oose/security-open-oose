package de.oose.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSecurity
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(prefix = "rest.security", value = "enabled", havingValue = "true")
@Import({ SecurityProperties.class })
public class SecurityConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private ResourceServerProperties resourceServerProperties;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceServerProperties.getResourceId());
	}

	@Override
	public void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers(securityProperties.getApiMatcher()).authenticated().and()
				.oauth2Login();
	}

	@Bean
	public JwtAccessTokenCustomizer jwtAccessTokenCustomizer(ObjectMapper mapper) {
		return new JwtAccessTokenCustomizer(mapper);
	}

}