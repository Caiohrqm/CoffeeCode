package br.com.fiap.coffeecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.coffeecode.models.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
