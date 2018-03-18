package br.com.bruno.skipthedishes.api.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class JWTAuthenticationFilter2 extends GenericFilterBean {
	
	private TokenHandler tokenHandler = new TokenHandler();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		// Delegates authentication to the TokenAuthenticationService
		Authentication authentication = getAuthentication((HttpServletRequest)request);
		
		// Apply the authentication to the SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Go on processing the request
		filterChain.doFilter(request,response);
		
		// Clears the context from authentication
		SecurityContextHolder.getContext().setAuthentication(null);
		
	}

	private Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(TokenHandler.HEADER_STRING);
		
		if (token != null && token.startsWith(TokenHandler.TOKEN_PREFIX)) {
			// Parse the token.
			String user = null;
			
			try {
				user = tokenHandler.parse(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			} else {
				return null;
			}

		}
		
		return null;
	}

}