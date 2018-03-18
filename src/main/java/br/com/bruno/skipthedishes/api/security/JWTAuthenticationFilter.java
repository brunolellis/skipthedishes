package br.com.bruno.skipthedishes.api.security;

import static br.com.bruno.skipthedishes.api.security.TokenHandler.HEADER_STRING;
import static br.com.bruno.skipthedishes.api.security.TokenHandler.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bruno.skipthedishes.customer.CustomerLogin;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private TokenHandler tokenHandler;
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenHandler tokenHandler) {
        this.authenticationManager = authenticationManager;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            CustomerLogin login = new ObjectMapper().readValue(req.getInputStream(), CustomerLogin.class);

            return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = tokenHandler.build(auth);
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }


}