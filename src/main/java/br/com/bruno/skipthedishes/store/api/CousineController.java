package br.com.bruno.skipthedishes.store.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.skipthedishes.api.rest.BaseV1RestController;
import br.com.bruno.skipthedishes.store.Cousine;
import br.com.bruno.skipthedishes.store.service.CousineService;

@RestController
public class CousineController extends BaseV1RestController {

	private CousineService cousineService;
	
	public CousineController(CousineService cousineService) {
		this.cousineService = cousineService; 
	}
	
	@GetMapping("/cousines")
	public List<Cousine> findAll() {
		return cousineService.findAll();
	}
	
}
