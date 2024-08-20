package com.loja.projetolojaweb2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean status;

    @Timestamp
    private LocalDateTime data;

    private String transportadora;

//    @OneToMany(mappedBy = "pedidos",fetch = FetchType.LAZY,targetEntity = Produto.class)
//    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "fk_pedidoUsuario")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<ItemPedido> itemPedido;

//    public void addProduto(Produto produto) {
//        this.produtos.add(produto);
//        produto.setPedidos(this);
//    }
}
