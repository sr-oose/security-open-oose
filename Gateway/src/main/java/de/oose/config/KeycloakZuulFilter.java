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
	    if (header == null) {
		    var securityContext = getRefreshableKeycloakSecurityContext(ctx);
		    if (securityContext != null) {
		    	String bearerToken = "Bearer " + securityContext.getTokenString();
		        ctx.addZuulRequestHeader(AUTHORIZATION_HEADER, bearerToken);
		    }
	    }
	    return null;
	}

	private RefreshableKeycloakSecurityContext getRefreshableKeycloakSecurityContext(RequestContext ctx) {
	    if (ctx.getRequest().getUserPrincipal() instanceof KeycloakAuthenticationToken) {
	        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) ctx.getRequest().getUserPrincipal();
	        return (RefreshableKeycloakSecurityContext) token.getCredentials();
	    }
	    return null;
	}
}
