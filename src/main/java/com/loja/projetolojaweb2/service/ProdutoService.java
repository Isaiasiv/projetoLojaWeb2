package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.Repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.ProdutoServicerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements ProdutoServicerInterface {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    @Override
    public List<Produto> findByTipo(String tipo) {
        return produtoRepository.findByTipo(tipo);
    }

    @Override
    public List<Produto> findByTamanho(String tamanho) {
        return produtoRepository.findByTamanho(tamanho);
    }

    @Override
    public List<Produto> findByCor(String cor) {
        return produtoRepository.findByCor(cor);
    }

    @Override
    public List<Produto> findByImagem(String imagem) {
        return produtoRepository.findByImagem(imagem);
    }

    @Override
    public List<Produto> findByFabricante(String fabricante) {
        return produtoRepository.findByFabricante(fabricante);
    }

    @Override
    public List<Produto> findByCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    @Override
    public List<Produto> findBySubcategoria(String subcategoria) {
        return produtoRepository.findBySubcategoria(subcategoria);
    }

    @Override
    public List<Produto> findByDescricao(String descricao) {
        return produtoRepository.findByDescricao(descricao);
    }

    @Override
    public List<Produto> findByValor(double valor) {
        return produtoRepository.findByValor(valor);
    }

    @Override
    public List<Produto> findByQuantidade(int quantidade) {
        return produtoRepository.findByQuantidade(quantidade);
    }

    @Override
    public String nome() {
        return "Produto Service";
    }

    @Override
    public void verificaProduto() {}

    @Override
    public void cadastrarProduto() {}

    @Override
    public void alterarProduto() {}

    @Override
    public void excluirProduto() {}

    @Override
    public void listarProdutos() {}
}
