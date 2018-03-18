package br.com.bruno.skipthedishes.customer;

import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class CustomerLoginController {

	
//	public LoginController(PaymentService paymentService) {
//		this.paymentService = paymentService;
//	}
//	
//	@PostMapping
//	@ResponseStatus(CREATED)
//	public PaymentDTO pay(@RequestBody PaymentDTO payment) {
//		return paymentService.pay(payment);
//	}
//	
//	@GetMapping
//	public PaymentDTO test() {
//		PaymentDTO payment = new PaymentDTO("from", "to", BigDecimal.ONE);
//		return paymentService.pay(payment);
//	}
}