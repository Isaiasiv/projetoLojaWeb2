package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.mapper.PessoaMapper;
import com.loja.projetolojaweb2.mapper.ProdutoMapper;
import com.loja.projetolojaweb2.repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.ProdutoServiceInterface;
import com.loja.projetolojaweb2.util.UtilConversor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto salvar(ProdutoPostRequest produtoPostRequest) {
        return produtoRepository.save(ProdutoMapper.INSTANCE.toProduto(produtoPostRequest));

    }

    @Override
    public void atualizar(ProdutoPutRequest produtoPutRequest) {
        Produto produtoSalvo = encontrarPorIdOuLancarExcecao(produtoPutRequest.getProdutoID().toString());
        Produto produto = ProdutoMapper.INSTANCE.toProduto(produtoPutRequest);
        produto.setProdutoID(produtoSalvo.getProdutoID());

        //if(carrinhoOptional.isPresent()) {
        // pessoa.setCarrinho(carrinhoOptional.get());
        // }
        produtoRepository.save(produto);
    }

    @Override
    public void delete(String produtoID) {
        produtoRepository.delete(encontrarPorIdOuLancarExcecao(produtoID));

    }

    @Override
    public Produto encontrarPorIdOuLancarExcecao(String produtoID) {
        return produtoRepository.findById(UtilConversor.convertToLong(produtoID))
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Pessoa não encontrada"));
    }

    public List<Produto> listAll() {
        return produtoRepository.findAll();
    }


}


