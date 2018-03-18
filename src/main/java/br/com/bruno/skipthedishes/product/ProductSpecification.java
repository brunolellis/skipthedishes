package br.com.bruno.skipthedishes.product;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class ProductSpecification {

    public static Specification<Product> nameOrDescription(String name) {
    	if (StringUtils.isEmpty(name)) {
    		return null;
    	}
    	
        return (root, query, builder) ->
        	builder.or(
        			builder.like(builder.lower(root.get(Product.NAME)), ilike(name)),
        			builder.like(builder.lower(root.get(Product.DESCRIPTION)), ilike(name))
    			);
    }
    
    public static Specification<Product> price(BigDecimal from, BigDecimal to) {
    	if (Objects.isNull(from) || Objects.isNull(to)) {
    		return null;
    	}
    	
        return (root, query, builder) ->
                builder.between(root.get(Product.PRICE), from, to);
    }

	private static String ilike(String text) {
		return "%" + text + "%";
	}
 
}
