package br.com.bruno.skipthedishes.api.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bruno.skipthedishes.customer.Customer;
import br.com.bruno.skipthedishes.customer.CustomerRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private CustomerRepository customerRepository;

	public UserDetailsServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepository.findByEmail(email);
		if (customer == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(customer.getEmail(), customer.getPassword(), emptyList());
	}
}