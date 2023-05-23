package br.com.fiap.coffeecode.models;

import org.springframework.hateoas.EntityModel;

import org.springframework.data.domain.Pageable;
import br.com.fiap.coffeecode.controllers.ItemPedidoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Pedido pedido;
    @NotBlank
    @Size(min = 5, max = 255)
    private String descricao;
    @NotNull
    @Min(1)
    private int quantidade;

    public EntityModel<ItemPedido> toEntityModel(){
        return EntityModel.of(
            this,
            linkTo(methodOn(ItemPedidoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ItemPedidoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ItemPedidoController.class).index(null, Pageable.unpaged())).withRel("all"),
            linkTo(methodOn(ItemPedidoController.class).show(this.getPedido().getId())).withRel("pedido")
        );
    }

}
