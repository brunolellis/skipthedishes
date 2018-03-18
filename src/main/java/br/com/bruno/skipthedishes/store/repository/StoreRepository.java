package br.com.bruno.skipthedishes.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.skipthedishes.store.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
