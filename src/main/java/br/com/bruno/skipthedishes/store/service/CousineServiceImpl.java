package br.com.bruno.skipthedishes.store.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.bruno.skipthedishes.store.Cousine;
import br.com.bruno.skipthedishes.store.repository.CousineRepository;

@Service
public class CousineServiceImpl implements CousineService {

	private CousineRepository cousineRepository;

	public CousineServiceImpl(CousineRepository cousineRepository) {
		this.cousineRepository = cousineRepository;
	}
	
	@Override
	public List<Cousine> findAll() {
		return cousineRepository.findAll();
	}
	
	

}
