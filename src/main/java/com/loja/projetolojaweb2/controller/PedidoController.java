package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPostRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.service.PedidoService;
import com.loja.projetolojaweb2.util.DataAutomatica;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(path = "/{id}")
    public Pedido findById(@PathVariable Long id) {
        return pedidoService.encontrarPorIdOuLancarExcecao(id);
    }

    @PostMapping()
    public ResponseEntity<Pedido> save(@RequestBody PedidoPostRequest pedidoPostRequest) {
        return new ResponseEntity<>(pedidoService.salvar(pedidoPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> list() {
        return ResponseEntity.ok(pedidoService.encontrarTodos());
    }

    @PutMapping()
    public ResponseEntity<Pedido> replace(@RequestBody PedidoPutRequest pedidoPutRequest) {
        pedidoService.atualizar(pedidoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Pedido> delete(@PathVariable Long id) {
        pedidoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/addProduto")
    public ResponseEntity<Pedido> addEnderecoToPessoa(@RequestBody ProdutoToPedidoDto produtoToPedidoDto) {
        Pedido pedido = pedidoService.addProdutoToPedido(produtoToPedidoDto);
        return ResponseEntity.ok(pedido);
    }

}
