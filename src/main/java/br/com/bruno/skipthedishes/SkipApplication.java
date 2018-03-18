package br.com.bruno.skipthedishes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.bruno.skipthedishes.customer.Customer;
import br.com.bruno.skipthedishes.customer.CustomerRepository;
import br.com.bruno.skipthedishes.order.Order;
import br.com.bruno.skipthedishes.order.OrderItem;
import br.com.bruno.skipthedishes.order.OrderStatus;
import br.com.bruno.skipthedishes.order.service.OrderService;
import br.com.bruno.skipthedishes.product.Product;
import br.com.bruno.skipthedishes.product.repository.ProductRepository;
import br.com.bruno.skipthedishes.store.Cousine;
import br.com.bruno.skipthedishes.store.Store;
import br.com.bruno.skipthedishes.store.repository.CousineRepository;
import br.com.bruno.skipthedishes.store.repository.StoreRepository;

@SpringBootApplication
public class SkipApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(SkipApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SkipApplication.class, args);
	}
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    @Autowired
    private CustomerRepository customers;
    
    @Autowired
    private ProductRepository products;
    
    @Autowired
    private CousineRepository cousines;
    
    @Autowired
    private StoreRepository stores;
    
    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... strings) throws Exception {

        log.info("loading initial data");
        
        Customer johnCustomer = new Customer();
        johnCustomer.setEmail("john@gmail.com");
        johnCustomer.setName("John");
        johnCustomer.setPassword(passwordEncoder.encode("temp"));
        customers.save(johnCustomer);

        Cousine chinese = new Cousine();
        chinese.setName("Chinese");
        cousines.save(chinese);
        
        Cousine pizza = new Cousine();
        pizza.setName("Pizza");
        cousines.save(pizza);
        
        Store ye = new Store();
        ye.setName("Ye's");
        ye.setAddress("616 St James St, Winnipeg, Manitoba R3G 3J5, Canada");
        ye.setCousine(chinese);
        ye = stores.save(ye);
        
        Store goodEarth = new Store();
        goodEarth.setName("Good Earth Chop Suey House");
        goodEarth.setAddress("1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada");
        goodEarth.setCousine(chinese);
        stores.save(goodEarth);
        
        Store za = new Store();
        za.setName("Za Pizza Bistro");
        za.setAddress("E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada");
        za.setCousine(pizza);
        stores.save(za);
        
        
        Product yakisoba = new Product();
        yakisoba.setName("Yakisoba");
        yakisoba.setDescription("Traditional Yakisoba");
        yakisoba.setPrice(new BigDecimal("22"));
        yakisoba.setStoreId(ye.getId());
		products.save(yakisoba);
        
		Product chicken = new Product();
        chicken.setName("Yakisoba - chicken");
        chicken.setDescription("Chicken yakisoba");
        chicken.setPrice(new BigDecimal("18"));
        chicken.setStoreId(ye.getId());
		products.save(chicken);
		
		Product pepperoni = new Product();
        pepperoni.setName("12\\\" Medium Deluxe Pizza");
        pepperoni.setDescription("Pepperoni, bacon, mushrooms, onions, and green peppers");
        pepperoni.setPrice(new BigDecimal("12.99"));
        pepperoni.setStoreId(za.getId());
		products.save(pepperoni);
		
		
		
		Order order = new Order();
		order.setContact("Please, suprise delivery to my mother!");
		order.setCreatedAt(LocalDateTime.now());
		order.setCustomerId(johnCustomer.getId());
		order.setDeliveryAddress("1 infinite loop");
		order.setStatus(OrderStatus.PENDING);
		order.setStoreId(za.getId());
		
		OrderItem item = new OrderItem();
		item.setPrice(pepperoni.getPrice());
		item.setProductId(pepperoni.getId());
		item.setQuantity(1);
		
		order.add(item);
		
		order = orderService.save(order);
		
    }
    
}
