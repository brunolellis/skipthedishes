package br.com.bruno.skipthedishes.product.api;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import br.com.bruno.skipthedishes.product.Product;

public class ProductsIT {

	private static final String PRODUCTS_URI = "/products";
	private static final String PRODUCTS_SEARCH_URI = PRODUCTS_URI + "/search";

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/api/v1";
	}
	
	@Test
	public void findAllProducts() {
		List<Product> products = given()
				.get(PRODUCTS_SEARCH_URI)
				.jsonPath()
				.get();
		
		Assert.assertEquals(3, products.size());
	}
	
	@Test
	public void findProductsByName() {
		List<Product> products = given()
				.queryParam("q", "yakisoba")
		    	.get(PRODUCTS_SEARCH_URI)
			    .jsonPath()
			    .get();
		
		Assert.assertEquals(2, products.size());
	}
	
	@Test
	public void findProductsByNameOrDescription() {
		List<Product> products = given()
				.queryParam("q", "BACON")
		    	.get(PRODUCTS_SEARCH_URI)
			    .jsonPath()
			    .get();
		
		Assert.assertEquals(1, products.size());
	}
	
	@Test
	public void findProductsByPrice() {
		List<Product> products = given()
				.queryParam("q", "yakisoba")
				.queryParam("priceFrom", "20")
				.queryParam("priceTo", "40")
		    	.get(PRODUCTS_SEARCH_URI)
			    .jsonPath()
			    .get();
		
		Assert.assertEquals(1, products.size());
	}

}
