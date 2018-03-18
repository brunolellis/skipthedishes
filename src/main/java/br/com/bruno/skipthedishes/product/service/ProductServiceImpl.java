package br.com.bruno.skipthedishes.product.service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import br.com.bruno.skipthedishes.product.Product;
import br.com.bruno.skipthedishes.product.ProductSpecification;
import br.com.bruno.skipthedishes.product.api.ProductSearchRequestDTO;
import br.com.bruno.skipthedishes.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> search(ProductSearchRequestDTO request) {
		LOGGER.info("searching products with request {}", request);
		
		Specifications<Product> where = where(ProductSpecification.nameOrDescription(request.getQ()))
				.and(ProductSpecification.price(request.getPriceFrom(), request.getPriceTo()));
		return productRepository.findAll(where);
		
	}

}
