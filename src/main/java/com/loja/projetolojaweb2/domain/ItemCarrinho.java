package com.loja.projetolojaweb2.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loja.projetolojaweb2.dto.carrinhoDto.ItemCarrinhoId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "item_carrinho")
//@IdClass(ItemCarrinhoId.class)
public class ItemCarrinho {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item_carrinho_id;


    //Se for mudar o nome dessas duas classes com @Id mudar também em ItemCarrinhoId
    //@Id
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    @JsonIgnore
    private Carrinho carrinho;

   // @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonIgnore
    private Produto produto;

    private int quantidade;
}
