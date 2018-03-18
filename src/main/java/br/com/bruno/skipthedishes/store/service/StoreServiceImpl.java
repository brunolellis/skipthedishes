package br.com.bruno.skipthedishes.store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bruno.skipthedishes.store.Store;
import br.com.bruno.skipthedishes.store.repository.StoreRepository;

@Service
public class StoreServiceImpl implements StoreService {

	private StoreRepository storeRepository;

	public StoreServiceImpl(StoreRepository storeRepository) {
		this.storeRepository = storeRepository;
	}
	
	@Override
	public Page<Store> findAll(Pageable pageable) {
		return storeRepository.findAll(pageable);
	}
	
	

}
