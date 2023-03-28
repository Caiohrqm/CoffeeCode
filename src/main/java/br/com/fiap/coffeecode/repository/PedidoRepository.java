package br.com.fiap.coffeecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.coffeecode.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
}
