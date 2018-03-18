package br.com.bruno.skipthedishes.product.api;

import java.math.BigDecimal;

public class ProductSearchRequestDTO {

	private String q;
	private BigDecimal priceFrom;
	private BigDecimal priceTo;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public BigDecimal getPriceTo() {
		return priceTo;
	}

	public void setPriceTo(BigDecimal priceTo) {
		this.priceTo = priceTo;
	}

	@Override
	public String toString() {
		return "ProductSearchRequestDTO [q=" + q + ", priceFrom=" + priceFrom + ", priceTo=" + priceTo + "]";
	}

}
