package br.com.bruno.skipthedishes.store.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.skipthedishes.api.rest.BaseV1RestController;
import br.com.bruno.skipthedishes.store.Store;
import br.com.bruno.skipthedishes.store.service.StoreService;

@RestController
public class StoreController extends BaseV1RestController {

	private StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService; 
	}
	
	@GetMapping("/stores")
	public Page<Store> findAll(Pageable pageable) {
		return storeService.findAll(pageable);
	}
	
}
