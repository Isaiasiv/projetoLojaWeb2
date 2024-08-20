package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPostRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.mapper.EnderecoMapper;
import com.loja.projetolojaweb2.mapper.PedidoMapper;
import com.loja.projetolojaweb2.repository.PedidoRepository;
import com.loja.projetolojaweb2.repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.PedidoServiceInterface;
import com.loja.projetolojaweb2.util.DataAutomatica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PedidoService implements PedidoServiceInterface {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido addProdutoToPedido(ProdutoToPedidoDto produtoToPedidoDto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoToPedidoDto.getIdProduto());
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(produtoToPedidoDto.getIdPedido());

        if (produtoOptional.isPresent() && pedidoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            Pedido pedido = pedidoOptional.get();
            pedido.addProduto(produto);
            produtoOptional.get().setQuantidade(produto.getQuantidade() - 1);
            return pedidoRepository.save(pedido);
        }

        throw new RuntimeException("Pedido ou Produto não encontrado");

    }

    @Override
    public List<Pedido> encontrarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido encontrarPorIdOuLancarExcecao(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST,"Pedido não encontrado"));
    }

    @Override
    public void atualizar(PedidoPutRequest pedidoPutRequest) {
        Pedido pedidoSalvo = encontrarPorIdOuLancarExcecao(pedidoPutRequest.getId());
        Pedido pedido = PedidoMapper.INSTANCE.toPedido(pedidoPutRequest);
        pedido.setId(pedidoSalvo.getId());
        pedidoRepository.save(pedido);

    }

    @Override
    public Pedido salvar(PedidoPostRequest pedidoPostRequest) {


        LocalDateTime dataAtual = LocalDateTime.now();
        pedidoPostRequest.setData(dataAtual);

        return pedidoRepository.save(PedidoMapper.INSTANCE.toPedido(pedidoPostRequest));
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.delete(encontrarPorIdOuLancarExcecao(id));

    }
}
