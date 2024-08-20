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
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
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

    @Operation(summary = "Busca pedidos",description = "busca todos pedidos realizados por um usuario",tags = "Pedido")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @GetMapping(path = "/{id}")
    public Pedido findById(@PathVariable Long id) {
        return pedidoService.encontrarPorIdOuLancarExcecao(id);
    }

    @Operation(summary = "Fazer pedido",description = "usuario realiza um novo pedido apos escolhe os produtos",tags = "Pedido")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @PostMapping()
    public ResponseEntity<Pedido> save(@RequestBody PedidoPostRequest pedidoPostRequest) {
        return new ResponseEntity<>(pedidoService.salvar(pedidoPostRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Busca pedidos",description = "busca todos pedidos",tags = "Pedido")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @GetMapping()
    public ResponseEntity<List<Pedido>> list() {
        return ResponseEntity.ok(pedidoService.encontrarTodos());
    }

    @Operation(summary = "Edita pedidos",description = "edita pedidos a ser realizado por um usuario",tags = "Pedido")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
    @PutMapping()
    public ResponseEntity<Pedido> replace(@RequestBody PedidoPutRequest pedidoPutRequest) {
        pedidoService.atualizar(pedidoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "deletar", description = " deleta um pedido por id",tags = "Pedido")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Pedido> delete(@PathVariable Long id) {
        pedidoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "adicionar produto",description = "adiciona um produto ao pedido",tags = "Pedido")
    /*@ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "funcionário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir funcionário")
    })*/
//    @PutMapping("/addProduto")
//    public ResponseEntity<Pedido> addProdutoToPedido(@RequestBody ProdutoToPedidoDto produtoToPedidoDto) {
//        Pedido pedido = pedidoService.addProdutoToPedido(produtoToPedidoDto);
//        return ResponseEntity.ok(pedido);
//    }

    @PostMapping("/criarPedido")
    public ResponseEntity<Pedido> criarPedido(HttpServletRequest request){
        //pedidoService.criarPedido(request);
        return ResponseEntity.ok(pedidoService.criarPedido(request));
    }

}
