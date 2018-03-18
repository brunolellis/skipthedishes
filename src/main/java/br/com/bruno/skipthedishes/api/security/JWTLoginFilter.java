package br.com.bruno.skipthedishes.api.security;

import static br.com.bruno.skipthedishes.api.security.TokenHandler.HEADER_STRING;
import static br.com.bruno.skipthedishes.api.security.TokenHandler.TOKEN_PREFIX;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bruno.skipthedishes.customer.CustomerLogin;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private TokenHandler tokenHandler = new TokenHandler();

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
		
        CustomerLogin login = new ObjectMapper().readValue(req.getInputStream(), CustomerLogin.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(), Collections.emptyList())
		);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
        String token = tokenHandler.build(auth);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}

}