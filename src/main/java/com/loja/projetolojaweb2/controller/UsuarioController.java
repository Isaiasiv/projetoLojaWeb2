package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Log4j2
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> listAll() {
        return ResponseEntity.ok(usuarioService.encontrarTodos());
    }

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return usuarioService.encontrarPorIdOuLancarExcecao(login);
    }

    @PostMapping()
    public ResponseEntity<Pessoa> save(@RequestBody UsuarioPostRequest usuarioPostRequest ) {
        usuarioPostRequest.setTipoConta(1);
        usuarioService.salvar(usuarioPostRequest);
        return new ResponseEntity<>(usuarioService.salvar(usuarioPostRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Usuario> delete(@PathVariable String login ) {
        usuarioService.delete(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Pessoa> replace(@RequestBody UsuarioPutRequest usuarioPutRequest) {
        usuarioService.atualizar(usuarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
