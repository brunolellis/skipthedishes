package br.com.bruno.skipthedishes.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.bruno.skipthedishes.customer.CustomerRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
    private JWTAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${security.basic.enabled}")
	private Boolean securityEnabled;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	if (!securityEnabled) {
    		http.authorizeRequests().antMatchers("/").permitAll();
			return;
    	}

    	
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			// Security policy
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, TokenHandler.LOGIN_URL).permitAll()
				.antMatchers(HttpMethod.POST, TokenHandler.SIGN_UP_URL).permitAll()
				// Any other request must be authenticated
				.anyRequest().authenticated().and()
			.addFilterBefore(new JWTLoginFilter(TokenHandler.LOGIN_URL, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean());
	}
	
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailsServiceImpl(customerRepository);
	}
	
	@Bean
	public TokenHandler tokenHandler() {
		return new TokenHandler();
	}
	
}