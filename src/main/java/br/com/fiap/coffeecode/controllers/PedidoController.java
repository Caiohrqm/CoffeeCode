package br.com.fiap.coffeecode.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.exceptions.RestNotFoundException;
import br.com.fiap.coffeecode.models.Pedido;
import br.com.fiap.coffeecode.repository.PedidoRepository;
import jakarta.validation.Valid;

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

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    PedidoRepository repository;

    @GetMapping
    public List<Pedido> index() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido pedido) {
        log.info("cadastrando pedido " + pedido);
        repository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pedido> show(@PathVariable Long id) {
        log.info("detalhando pedido " + id);
        return ResponseEntity.ok(getPedido(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Pedido> destroy(@PathVariable Long id) {
        log.info("deletando pedido " + id);
        repository.delete(getPedido(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        log.info("atualizando pedido " + id);
        getPedido(id);
        pedido.setId(id);
        repository.save(pedido);
        return ResponseEntity.ok(pedido);
    }

    private Pedido getPedido(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("pedido não encontrado"));
    }

}
