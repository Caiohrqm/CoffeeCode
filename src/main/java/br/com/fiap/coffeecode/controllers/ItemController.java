package br.com.fiap.coffeecode.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.models.Item;
import br.com.fiap.coffeecode.repository.ItemRepository;

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
@RequestMapping("/menu")
public class ItemController {
    
    Logger log = LoggerFactory.getLogger(Item.class);

    @Autowired
    ItemRepository repository;

    @GetMapping
    public List<Item> index(){
        return repository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<Item> create(@RequestBody Item item){
        log.info("cadastrando item " + item);
        
        repository.save(item);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> show(@PathVariable Long id){
        log.info("detalhando item " + id);
        var itemEncontrado = repository.findById(id);
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok(itemEncontrado.get());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Item> destroy(@PathVariable Long id){
        log.info("deletando item " + id);
        var itemEncontrado = repository.findById(id);
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        
        repository.delete(itemEncontrado.get());

        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item){
        log.info("atualizando item " + id);
        var itemEncontrado = repository.findById(id);
        
        if (itemEncontrado.isEmpty())
            return ResponseEntity.notFound().build();
        
        item.setId(id);
        repository.save(item);

        return ResponseEntity.ok(item);
    }

}
