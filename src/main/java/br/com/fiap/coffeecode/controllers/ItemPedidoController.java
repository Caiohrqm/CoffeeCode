package br.com.fiap.coffeecode.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.exceptions.RestNotFoundException;
import br.com.fiap.coffeecode.models.ItemPedido;
import br.com.fiap.coffeecode.repository.ItemPedidoRepository;
import br.com.fiap.coffeecode.repository.ItemRepository;
import br.com.fiap.coffeecode.repository.PedidoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cesta")
public class ItemPedidoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @GetMapping
    public List<ItemPedido> index() {
        return itemPedidoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ItemPedido itemPedido) {
        log.info("cadastrando associação de item e pedido " + itemPedido);
        itemPedidoRepository.save(itemPedido);
        itemPedido.setItem(itemRepository.findById(itemPedido.getItem().getId()).get());
        itemPedido.setPedido(pedidoRepository.findById(itemPedido.getPedido().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedido);
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemPedido> show(@PathVariable Long id) {
        log.info("detalhando associação " + id);
        return ResponseEntity.ok(getItemPedido(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ItemPedido> destroy(@PathVariable Long id) {
        log.info("apagando associação " + id);
        itemPedidoRepository.delete(getItemPedido(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ItemPedido> update(@PathVariable Long id, @RequestBody @Valid ItemPedido itemPedido) {
        log.info("atualizando associação " + id);
        getItemPedido(id);
        itemPedido.setId(id);
        itemPedidoRepository.save(itemPedido);
        return ResponseEntity.ok(itemPedido);
    }

    private ItemPedido getItemPedido(Long id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("associação não encontrada"));
    }

}
