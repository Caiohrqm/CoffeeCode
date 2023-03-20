package br.com.fiap.coffeecode.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.models.Pedido;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PedidoController {
    
    Logger log = LoggerFactory.getLogger(Pedido.class);
    List<Pedido> pedidos = new ArrayList<>();

    @GetMapping("/pedido")
    public List<Pedido> index(){
        return pedidos;
    }
    
    @PostMapping("/pedido")
    public ResponseEntity<Pedido> create(@RequestBody Pedido pedido){
        log.info("cadastrando pedido " + pedido);
        pedido.setId(pedidos.size() + 1l);
        pedidos.add(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<Pedido> show(@PathVariable Long id){
        log.info("detalhando pedido " + id);
        var itemEncontrado = pedidos.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        return ResponseEntity.ok(itemEncontrado.get());
    }
    
    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Pedido> destroy(@PathVariable Long id){
        log.info("deletando pedido " + id);
        var itemEncontrado = pedidos.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        pedidos.remove(itemEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/pedido/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido){
        log.info("atualizando pedido " + id);
        var itemEncontrado = pedidos.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        pedidos.remove(itemEncontrado.get());
        pedido.setId(id);
        pedidos.add(pedido);

        return ResponseEntity.ok(pedido);
    }

}
