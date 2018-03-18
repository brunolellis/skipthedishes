package br.com.bruno.skipthedishes.product.api;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import br.com.bruno.skipthedishes.customer.CustomerLogin;

public class CustomersIT {

	private static final String CUSTOMERS_URI = "/customers";
	private static final String CUSTOMERS_LOGIN_URI = "/customers/login";

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/api/v1";
	}
	
	@Test
	@Ignore
	public void shouldCreateCustomer() {
		CustomerLogin login = new CustomerLogin();
		login.setEmail("john@gmail.com");
		login.setPassword("temp");
		given()
			.body(login)
		.post(CUSTOMERS_URI);
		
	}
	
	@Test
	public void shouldLogin() {
		CustomerLogin login = new CustomerLogin();
		login.setEmail("john@gmail.com");
		login.setPassword("temp");
		String token = given()
			.header("Content-type", "application/json")
			.body(login)
		.post(CUSTOMERS_LOGIN_URI)
		.as(String.class);
		
		System.out.println(token);
		
	}
	
	@Test
	public void shouldNotLoginWithWrongPassword() {
		CustomerLogin login = new CustomerLogin();
		login.setEmail("john@gmail.com");
		login.setPassword("!");
		given()
			.header("Content-type", "application/json")
			.body(login)
		.post(CUSTOMERS_LOGIN_URI);
		
	}

}
