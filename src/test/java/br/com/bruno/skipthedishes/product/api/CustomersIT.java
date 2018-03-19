package br.com.bruno.skipthedishes.product.api;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import br.com.bruno.skipthedishes.customer.Customer;
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
	public void shouldCreateCustomer() {
		Customer newCustomer = new Customer();
		newCustomer.setEmail("a");
		newCustomer.setPassword("a");
		newCustomer.setAddress("address");
		newCustomer.setName("name");
		
		given()
			.body(newCustomer)
		.post(CUSTOMERS_URI);
		
	}
	
	@Test
	public void shouldLogin() {
		CustomerLogin login = new CustomerLogin();
		login.setEmail("john@gmail.com");
		login.setPassword("temp");
		given()
			.header("Content-type", "application/json")
			.body(login)
		.post(CUSTOMERS_LOGIN_URI)
		.then().statusCode(200);
		
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
