package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.service.FuncionarioService;
import com.loja.projetolojaweb2.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/funcionarios")
@RestController
@Log4j2
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return funcionarioService.encontrarPorIdOuLancarExcecao(login);
    }

    @PostMapping()
    public ResponseEntity<Funcionario> save(@RequestBody FuncionarioPostRequest funcionarioPostRequest) {
        funcionarioPostRequest.setTipoConta(2);
        funcionarioService.salvar(funcionarioPostRequest);
        return new ResponseEntity<>(funcionarioService.salvar(funcionarioPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Funcionario>> list() {
        return ResponseEntity.ok(funcionarioService.encontrarTodos());
    }

    @PutMapping()
    public ResponseEntity<Funcionario> replace(@RequestBody FuncionarioPutRequest funcionarioPutRequest) {
        funcionarioService.atualizar(funcionarioPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Funcionario> delete(@PathVariable String login) {
        funcionarioService.delete(login);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
