package br.com.bruno.skipthedishes.product.api;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import br.com.bruno.skipthedishes.api.security.TokenHandler;
import br.com.bruno.skipthedishes.product.Product;

public class CousinesIT {

	private static final String COUSINES_URI = "/cousines";

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/api/v1";
	}
	
	@Test
	public void findAll() {
		List<Product> products = given()
				.get(COUSINES_URI)
				.jsonPath()
				.get();
		
		Assert.assertEquals(2, products.size());
		
	}
	
	@Test
	public void shouldReturnForbidden() {
		given()
			.get(COUSINES_URI)
			.then()
				.statusCode(HttpStatus.SC_FORBIDDEN);
		
	}
	
	@Test
	public void shouldReturnUnauthorizedWithInvalidToken() {
		given()
			.header(TokenHandler.HEADER_STRING, TokenHandler.TOKEN_PREFIX + "123456")
		.get(COUSINES_URI)
		.then()
			.statusCode(HttpStatus.SC_FORBIDDEN);
		
	}
	
	@Test
	public void shouldReturnUnauthorizedWithInvalidTokenWellFormed() {
		given()
			.header(TokenHandler.HEADER_STRING, TokenHandler.TOKEN_PREFIX + "123456.11111.222222")
		.get(COUSINES_URI)
		.then()
			.statusCode(HttpStatus.SC_FORBIDDEN);
		
	}
	
}
