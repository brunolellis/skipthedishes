package br.com.bruno.skipthedishes.customer.api;

import javax.validation.Valid;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.skipthedishes.api.rest.BaseV1RestController;
import br.com.bruno.skipthedishes.api.security.TokenHandler;
import br.com.bruno.skipthedishes.customer.Customer;
import br.com.bruno.skipthedishes.customer.CustomerLogin;
import br.com.bruno.skipthedishes.customer.CustomerRepository;

@RestController
public class CustomerController extends BaseV1RestController {

	private CustomerRepository customerRepository;
	private PasswordEncoder encoder;

	public CustomerController(CustomerRepository customerRepository,
			PasswordEncoder bCryptPasswordEncoder) {
		this.customerRepository = customerRepository;
		this.encoder = bCryptPasswordEncoder;
	}

	@PostMapping(TokenHandler.SIGN_UP_URL)
	public void create(@Valid Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		customerRepository.save(customer);
	}
	
//	@PostMapping("/customers/login")
//	public void login(@Valid CustomerLogin login) {
////		customer.setPassword(encoder.encode(customer.getPassword()));
////		customerRepository.save(customer);
//	}

}
