package br.com.fiap.coffeecode.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.models.Item;
import br.com.fiap.coffeecode.models.Pedido;

@RestController
public class PedidoController {
    
    @GetMapping("/pedido")
    public Pedido show(){
        return new Pedido(2, new ArrayList<>(Arrays.asList(new Item(3, new BigDecimal(6.5), 2))) , new BigDecimal(13), LocalDateTime.of(2023, Month.MARCH, 07, 10, 8, 46), 2, "Maria", false);
    }
}
