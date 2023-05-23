package br.com.fiap.coffeecode.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
//import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coffeecode.exceptions.RestNotFoundException;
import br.com.fiap.coffeecode.models.ItemPedido;
import br.com.fiap.coffeecode.repository.ItemPedidoRepository;
import br.com.fiap.coffeecode.repository.ItemRepository;
import br.com.fiap.coffeecode.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cesta")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "item pedido")
public class ItemPedidoController {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    ItemRepository itemRepository;
 
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
        Page<ItemPedido> itensPedido = (busca == null)?
            itemPedidoRepository.findAll(pageable):
            itemPedidoRepository.findByDescricaoContaining(busca, pageable);

            return assembler.toModel(itensPedido.map(ItemPedido::toEntityModel));

    }


    @PostMapping
    @ApiResponses ({
        @ApiResponse(responseCode = "201", description = "Item pedido cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Os campos enviados são inválidos")
    })
    public ResponseEntity<Object> create(@RequestBody @Valid ItemPedido itemPedido) {
        log.info("cadastrando associação de item e pedido " + itemPedido);
        itemPedidoRepository.save(itemPedido);
        itemPedido.setItem(itemRepository.findById(itemPedido.getItem().getId()).get());
        itemPedido.setPedido(pedidoRepository.findById(itemPedido.getPedido().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedido.toEntityModel());
    }

    @GetMapping("{id}")
    @Operation(
        summary = "Detalhar item pedido",
        description = "Endpoint que recebe um id e retorna os dados da item pedido. O id deve ser ..."

    )
    public EntityModel<ItemPedido> show(@PathVariable Long id) {
        log.info("detalhando associação " + id);
        var itemPedido = getItemPedido(id);
        return itemPedido.toEntityModel();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ItemPedido> destroy(@PathVariable Long id) {
        log.info("apagando associação " + id);
        var itemPedido = getItemPedido(id);
        itemPedidoRepository.delete(itemPedido);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public EntityModel<ItemPedido> update(@PathVariable Long id, @RequestBody @Valid ItemPedido itemPedido) {
        log.info("atualizando associação " + id);
        getItemPedido(id);
        itemPedido.setId(id);
        itemPedidoRepository.save(itemPedido);
        return itemPedido.toEntityModel();
    }

    private ItemPedido getItemPedido(Long id) {
        return itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RestNotFoundException("associação não encontrada"));
    }

}
