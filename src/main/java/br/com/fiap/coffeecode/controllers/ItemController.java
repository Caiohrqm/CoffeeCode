package br.com.fiap.coffeecode.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.models.Item;

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
public class ItemController {
    
    Logger log = LoggerFactory.getLogger(Item.class);
    List<Item> itens = new ArrayList<>();

    @GetMapping("/menu")
    public List<Item> index(){
        return itens;
    }
    
    @PostMapping("/menu")
    public ResponseEntity<Item> create(@RequestBody Item item){
        log.info("cadastrando item " + item);
        item.setId(itens.size() + 1l);
        itens.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Item> show(@PathVariable Long id){
        log.info("detalhando item " + id);
        var itemEncontrado = itens.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        return ResponseEntity.ok(itemEncontrado.get());
    }
    
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Item> destroy(@PathVariable Long id){
        log.info("deletando item " + id);
        var itemEncontrado = itens.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        itens.remove(itemEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping("/menu/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item){
        log.info("atualizando item " + id);
        var itemEncontrado = itens.stream().filter(i -> i.getId().equals(id)).findFirst();
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
        itens.remove(itemEncontrado.get());
        item.setId(id);
        itens.add(item);

        return ResponseEntity.ok(item);
    }

}
