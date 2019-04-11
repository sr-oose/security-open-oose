package de.oose.config;

import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

class KeycloakZuulFilter extends ZuulFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";

	@Override
	public String filterType() {
	    return "pre";
	}

	@Override
	public int filterOrder() {
	    return 1;
	}

	@Override
	public boolean shouldFilter() {
	    return true;
	}

	@Override
	public Object run() {
	    RequestContext ctx = RequestContext.getCurrentContext();
	    String header = ctx.getRequest().getHeader(AUTHORIZATION_HEADER);
	    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX\nHeader: " + header);
	    if (header == null) {
		    var securityContext = getRefreshableKeycloakSecurityContext(ctx);
		    if (securityContext != null) {
		    	String bearerToken = buildBearerToken(securityContext);
		    	System.out.println("Adding authorization header");
		        ctx.addZuulRequestHeader(AUTHORIZATION_HEADER, bearerToken);
		    }
	    }
	    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	    return null;
	}

	private RefreshableKeycloakSecurityContext getRefreshableKeycloakSecurityContext(RequestContext ctx) {
	    if (ctx.getRequest().getUserPrincipal() instanceof KeycloakAuthenticationToken) {
	        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) ctx.getRequest().getUserPrincipal();
	        return (RefreshableKeycloakSecurityContext) token.getCredentials();
	    }
	    return null;
	}

	private String buildBearerToken(RefreshableKeycloakSecurityContext securityContext) {
	    return "Bearer " + securityContext.getTokenString();
	}
}
