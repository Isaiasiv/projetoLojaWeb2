package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Produto;

import java.util.List;

public interface ProdutoServicerInterface {

    public String nome();

    public void verificaProduto();

    public void cadastrarProduto();

    public void alterarProduto();

    public void excluirProduto();

    public void listarProdutos();

    public List<Produto> findByNome(String nome);

    public List<Produto> findByTipo(String tipo);

    public List<Produto> findByTamanho(String tamanho);

    public List<Produto> findByCor(String cor);

    public List<Produto> findByImagem(String imagem);

    public List<Produto> findByFabricante(String fabricante);

    public List<Produto> findByCategoria(String categoria);

    public List<Produto> findBySubcategoria(String subcategoria);

    public List<Produto> findByDescricao(String descricao);

    public List<Produto> findByValor(double valor);

    public List<Produto> findByQuantidade(int quantidade);
}
