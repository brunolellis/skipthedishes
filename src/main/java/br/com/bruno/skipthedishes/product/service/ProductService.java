package br.com.bruno.skipthedishes.product.service;

import java.util.List;

import br.com.bruno.skipthedishes.product.Product;
import br.com.bruno.skipthedishes.product.api.ProductSearchRequestDTO;

public interface ProductService {

	List<Product> search(ProductSearchRequestDTO request);

}
