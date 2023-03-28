package br.com.fiap.coffeecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.coffeecode.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
