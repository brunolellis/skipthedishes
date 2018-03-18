package br.com.bruno.skipthedishes.customer;

import static br.com.bruno.skipthedishes.api.rest.BaseV1RestController.V1_URI;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.skipthedishes.api.rest.BaseV1RestController;

@RestController
@RequestMapping(V1_URI + "/customers")
public class CustomerController extends BaseV1RestController {

	private CustomerRepository customerRepository;

	public CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}


}
