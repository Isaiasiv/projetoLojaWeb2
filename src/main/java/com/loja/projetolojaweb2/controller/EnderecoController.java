package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Funcionario;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPostRequest;
import com.loja.projetolojaweb2.dto.funcionarioDto.FuncionarioPutRequest;
import com.loja.projetolojaweb2.service.EnderecoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("api/v1/enderecos"))
@Data
@RequiredArgsConstructor
@Log4j2
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping(path = "/{id}")
    public Endereco findById(@PathVariable Long id) {
        return enderecoService.encontrarPorIdOuLancarExcecao(id);
    }

    @PostMapping()
    public ResponseEntity<Endereco> save(@RequestBody EnderecoPostRequest enderecoPostRequest) {
        return new ResponseEntity<>(enderecoService.salvar(enderecoPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Endereco>> list() {
        return ResponseEntity.ok(enderecoService.encontrarTodos());
    }

    @PutMapping()
    public ResponseEntity<Endereco> replace(@RequestBody EnderecoPutRequest enderecoPutRequest) {
        enderecoService.atualizar(enderecoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Endereco> delete(@PathVariable Long id) {
        enderecoService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
