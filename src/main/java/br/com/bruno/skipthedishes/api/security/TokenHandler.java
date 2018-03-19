package br.com.bruno.skipthedishes.api.security;

import java.util.Date;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenHandler {
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	private static final String SECRET = "e2d8fd0c-f13c- SECReT -478b-ac6b-b42aa19477b2";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days in ms
	public static final String SIGN_UP_URL = "/api/v1/customers";
	public static final String LOGIN_URL = "/api/v1/customers/login";
	
	public String parse(String token) {
		String user = Jwts.parser()
		        .setSigningKey(SECRET)
		        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
		        .getBody()
		        .getSubject();
		return user;
	}

	public String build(Authentication auth) {
		String token = Jwts.builder()
				.setId(UUID.randomUUID().toString())
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
		return token;
	}
	
}
