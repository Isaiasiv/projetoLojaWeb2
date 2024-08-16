package com.loja.projetolojaweb2.dto.produtoDto;

import lombok.Data;

@Data
public class ProdutoPutRequest {

    private int produtoID;
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
