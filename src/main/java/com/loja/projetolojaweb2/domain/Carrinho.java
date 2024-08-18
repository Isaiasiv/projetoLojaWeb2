package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loja.projetolojaweb2.Exceptions.ProdutoNotFoundException;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carrinho")

public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "carrinho",fetch = FetchType.LAZY,targetEntity = Produto.class)
//    private List<Produto> produtos;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrinhoProduto> produtos = new ArrayList<>();

    private BigDecimal valorTotal;
    private String categoria;

    @OneToOne(mappedBy = "carrinho")
    @JsonIgnore
    private Pessoa pessoa;

//    public void addProduto(Produto produto){
//      this.produtos.add(produto);
//     produto.setCarrinho(this);
//    }
//
    public void removerTodosProdutos(Produto produto){
        this.produtos.remove(produto);
        produto.setCarrinhos(null);
    }

    public void removerProdutoQntd(Produto produto, int quantidade){
        for(CarrinhoProduto c : produtos){
            if(c.getProduto().equals(produto)){
                if(quantidade > c.getQuantidade()){
                    produtos.remove(c);
                }else {
                    c.setQuantidade(c.getQuantidade() - quantidade);
                }
                return;
            }
        }
        throw new ProdutoNotFoundException("Produto nao encontrado");
    }

    public void addProduto(Produto produto, int quantidade){
        for(CarrinhoProduto produtoCarrinho : produtos){
            if(produtoCarrinho.getProduto().equals(produto)){
                produtoCarrinho.setQuantidade(produtoCarrinho.getQuantidade() + quantidade);
                return;
            }
        }
        produtos.add(new CarrinhoProduto(this,produto,quantidade));
    }
//eu quero chorar de verdade, depois de 12horas seguidas mexendo nessa classe maldita, finalmente acho q ela ta funcionando
    //podemos ser felizes agora

}
