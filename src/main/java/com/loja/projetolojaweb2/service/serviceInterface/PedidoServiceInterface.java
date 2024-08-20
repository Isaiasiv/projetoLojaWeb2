package com.loja.projetolojaweb2.service.serviceInterface;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPostRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PedidoServiceInterface {

    //Pedido addProdutoToPedido(ProdutoToPedidoDto produtoToPedidoDto);
    Pedido criarPedido(HttpServletRequest request);

    List<Pedido> encontrarTodos();

    Pedido encontrarPorIdOuLancarExcecao(Long id);

    void atualizar(PedidoPutRequest pedidoPutRequest);

    Pedido salvar(PedidoPostRequest pedidoPostRequest);

    void delete(Long id);
}
