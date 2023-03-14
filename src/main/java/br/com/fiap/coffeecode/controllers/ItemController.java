package br.com.fiap.coffeecode.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.models.Item;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ItemController {
    
    @GetMapping("/menu")
    public Item show(){
        return new Item(1,"Bebidas","Expresso","Café preto",new BigDecimal(3.5),true);

    }
    
}
