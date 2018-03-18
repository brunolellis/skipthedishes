package br.com.bruno.skipthedishes.product.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.skipthedishes.api.rest.BaseV1RestController;
import br.com.bruno.skipthedishes.product.Product;
import br.com.bruno.skipthedishes.product.service.ProductService;

@RestController
public class ProductController extends BaseV1RestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService; 
	}
	
	@GetMapping("/products/search")
	@ResponseBody
	public List<Product> search(ProductSearchRequestDTO request) {
		List<Product> products = productService.search(request);
		return products;
	}
	
}
