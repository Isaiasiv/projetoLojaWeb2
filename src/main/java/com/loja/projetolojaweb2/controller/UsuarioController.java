package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Pedido;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.peditoToUsuarioDto.PedidoToUsuarioDto;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.service.EnderecoService;
import com.loja.projetolojaweb2.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> listAll() {
        return ResponseEntity.ok(usuarioService.encontrarTodos());
    }

    @Operation(summary = "Busca Usuário",description = "Busca um Usuário atraves do nome de usuario(login),",tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir Usuário")
    })
    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return usuarioService.encontrarPorIdOuLancarExcecao(login);
    }

    @Operation(summary = "Criar Usuário",description = "Cria uma nova conta de Usuário",tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir Usuário")
    })
    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequest usuarioPostRequest ) {
        usuarioPostRequest.setTipoConta(1);
        return new ResponseEntity<>(usuarioService.salvar(usuarioPostRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Excluir Usuário",description = "exclui conta de um Usuárioatraves do nome de usuario(login)",tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir Usuário")
    })
    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Usuario> delete(@PathVariable String login ) {
        usuarioService.delete(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Edita Usuário",description = "Atualiza dados de  Usuárioatraves do nome de usuario(login)",tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir Usuário")
    })
    @PutMapping()
    public ResponseEntity<Usuario> replace(@RequestBody UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizar(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "pedidos do usuario",description = "lista de pedidos realizados por esse usuario",tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Usuário excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir Usuário")
    })
    @PutMapping("/addPedido")
    public ResponseEntity<Usuario> addPedidoToUsuario(@RequestBody PedidoToUsuarioDto pedidoToUsuarioDto) {
        Usuario usuario = usuarioService.addPedidoToUsuario(pedidoToUsuarioDto);
        return ResponseEntity.ok(usuario);
    }

}
