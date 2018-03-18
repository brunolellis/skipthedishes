package br.com.bruno.skipthedishes.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bruno.skipthedishes.order.Order;
import br.com.bruno.skipthedishes.order.OrderItem;
import br.com.bruno.skipthedishes.order.OrderStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void shouldTestOrderTotal() {
		Order order = new Order();
		order.setContact("Please, suprise delivery to my mother!");
		order.setCreatedAt(LocalDateTime.now());
		order.setCustomerId(1L);
		order.setDeliveryAddress("1 infinite loop");
		order.setStatus(OrderStatus.PENDING);
		order.setStoreId(1L);
		
		OrderItem pizza = new OrderItem();
		pizza.setPrice(new BigDecimal("12.99"));
		pizza.setProductId(1L);
		pizza.setQuantity(2);
		
		OrderItem coke = new OrderItem();
		coke.setPrice(new BigDecimal("3.99"));
		coke.setProductId(99L);
		coke.setQuantity(4);
		
		order.add(pizza);
		order.add(coke);
		
		order = orderService.save(order);

		Assert.assertEquals(new BigDecimal("41.94"), order.getTotal());
		
		
		BigDecimal itemsTotal = order.getOrderItems().stream()
				.filter(item -> item.getProductId().equals(1L))
				.map(OrderItem::getTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		Assert.assertEquals(new BigDecimal("25.98"), itemsTotal);
	}

}
