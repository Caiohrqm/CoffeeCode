package br.com.fiap.coffeecode.config;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.coffeecode.models.Item;
import br.com.fiap.coffeecode.models.ItemPedido;
import br.com.fiap.coffeecode.models.Pedido;
import br.com.fiap.coffeecode.repository.ItemPedidoRepository;
import br.com.fiap.coffeecode.repository.ItemRepository;
import br.com.fiap.coffeecode.repository.PedidoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item(1l, "Bebidas", "Expresso", "Café Preto", new BigDecimal(3.5), true),
                new Item(2l, "Bebidas", "Cappuccino", "Café com leite e chantilly", new BigDecimal(5), true),
                new Item(3l, "Salgados", "Coxinha", "Recheada com frango e catupity", new BigDecimal(6.5), true)));
        pedidoRepository.saveAll(List.of(
                new Pedido(1l, new BigDecimal(10),
                        LocalDateTime.parse("2023-03-07 09:52:33", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        1, "João", true),
                new Pedido(2l, new BigDecimal(13),
                        LocalDateTime.parse("2023-03-07 10:08:46", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        2, "Maria", false)));
        itemPedidoRepository.saveAll(List.of(
                new ItemPedido(1l,
                        new Item(1l, "Bebidas", "Expresso", "Café Preto", new BigDecimal(3.5), true),
                        new Pedido(1l, new BigDecimal(10),
                                LocalDateTime.parse("2023-03-07 09:52:33",
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                1, "João", true),
                        1),
                new ItemPedido(2l,
                        new Item(3l, "Salgados", "Coxinha", "Recheada com frango e catupity", new BigDecimal(6.5),
                                true),
                        new Pedido(1l, new BigDecimal(10),
                                LocalDateTime.parse("2023-03-07 09:52:33",
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                                1, "João", true),
                        1)));
    }

}
