package de.oose;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

//@EnableOAuth2Sso
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {
	private static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Component
	@Order(Ordered.HIGHEST_PRECEDENCE)
	class WorkaroundRestTemplateCustomizer implements UserInfoRestTemplateCustomizer {

	@Override
	public void customize(OAuth2RestTemplate template) {
		template.setInterceptors(new ArrayList<>(template.getInterceptors()));
	}
	}

}
