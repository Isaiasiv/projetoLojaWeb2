package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.Repository.ProdutoRepository;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.mapper.ProdutoMapper;
import com.loja.projetolojaweb2.service.serviceInterface.ProdutoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoServiceInterface {


    private final ProdutoRepository produtoRepository;

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
    public Produto salvar(ProdutoPostRequest produtoPostRequest) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequest));
    }
    @Override
    public void atualizar(ProdutoPutRequest produtoPutRequest) {
        Produto produtoSalvo = encontrarPorIdOuLancarExcecao(String.valueOf(produtoPutRequest.getProdutoID()));
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequest);
        produto.setProdutoID(produtoSalvo.getProdutoID());
        produtoRepository.save(produto);
    }

    @Override
    public void delete(String produtoID) {
        produtoRepository.delete(encontrarPorIdOuLancarExcecao(produtoID));
    }
    public List<Produto> listAll() {
        return  produtoRepository.findAll();
    }

    //regras
    @Override
    public Produto encontrarPorIdOuLancarExcecao(String produtoID) {
        return produtoRepository.findById(produtoID)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Produto não encontrado"));
    }


}
