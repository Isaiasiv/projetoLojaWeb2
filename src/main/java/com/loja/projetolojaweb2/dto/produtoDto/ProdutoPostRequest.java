package com.loja.projetolojaweb2.dto.produtoDto;

import lombok.Data;

@Data
public class ProdutoPostRequest {

    private Long produtoID; // Renomeado para corresponder ao campo na entidade Produto
    private String nome;
    private String tipo;
    private String tamanho;
    private String cor;
    private String imagem;
    private String fabricante;
    private String categoria;
    private String subcategoria;
    private String descricao;
    private double valor;
    private int quantidade;
}
