package br.com.bruno.skipthedishes.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.skipthedishes.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
