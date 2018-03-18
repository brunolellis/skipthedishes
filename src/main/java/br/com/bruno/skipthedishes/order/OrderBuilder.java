package br.com.bruno.skipthedishes.order;

import java.time.LocalDateTime;

public class OrderBuilder {
	
	private Order order = new Order();
	
	public OrderBuilder withDate(LocalDateTime date) {
		order.setCreatedAt(date);
		return this;
	}
	
	public Order build() {
		return order;
	}

}
