package br.com.bruno.skipthedishes.store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bruno.skipthedishes.store.Store;

public interface StoreService {
	
	Page<Store> findAll(Pageable pageable);

}
