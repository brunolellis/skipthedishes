package br.com.bruno.skipthedishes.order.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.bruno.skipthedishes.order.Order;
import br.com.bruno.skipthedishes.order.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public Order save(Order order) {
		BigDecimal total = order.getOrderItems().stream()
			.map(item -> {
				item.setOrder(order);
				BigDecimal totalItem = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
				item.setTotal(totalItem);
				return totalItem;
			})
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		order.setTotal(total);
		
		return orderRepository.save(order);
	}

	
	
}
