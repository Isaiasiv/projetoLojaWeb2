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

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return usuarioService.encontrarPorIdOuLancarExcecao(login);
    }

    @PostMapping()
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequest usuarioPostRequest ) {
        usuarioPostRequest.setTipoConta(1);
        return new ResponseEntity<>(usuarioService.salvar(usuarioPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Usuario> delete(@PathVariable String login ) {
        usuarioService.delete(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Usuario> replace(@RequestBody UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizar(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/addPedido")
    public ResponseEntity<Usuario> addPedidoToUsuario(@RequestBody PedidoToUsuarioDto pedidoToUsuarioDto) {
        Usuario usuario = usuarioService.addPedidoToUsuario(pedidoToUsuarioDto);
        return ResponseEntity.ok(usuario);
    }

}
