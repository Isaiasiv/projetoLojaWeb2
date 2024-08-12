package com.loja.projetolojaweb2.repository;

import com.loja.projetolojaweb2.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>{
    List<Produto> findByNome(String nome);
    List<Produto> findByTipo(String tipo);
    List<Produto> findByTamanho(String tamanho);
    List<Produto> findByCor(String cor);
    List<Produto> findByImagem(String imagem);
    List<Produto> findByFabricante(String fabricante);
    List<Produto> findByCategoria(String categoria);
    List<Produto> findBySubcategoria(String subcategoria);
    List<Produto> findByDescricao(String descricao);
    List<Produto> findByValor(double valor);
    List<Produto> findByQuantidade(int quantidade);
}
