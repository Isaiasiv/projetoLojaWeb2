package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.service.PessoaService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pessoas")
@Log4j2
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping(path="/{login}")
    public Pessoa findById(@PathVariable String login) {
        return pessoaService.encontrarPorIdOuLancarExcecao(login);
    }

    @PostMapping()
    public ResponseEntity<Pessoa> save(@RequestBody PessoaPostRequest pessoaPostRequest) {
        pessoaService.salvar(pessoaPostRequest);
    return new ResponseEntity<>(pessoaService.salvar(pessoaPostRequest), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Pessoa>> list() {
        return ResponseEntity.ok(pessoaService.listAll());
    }

    @PutMapping()
    public ResponseEntity<Pessoa> replace(@RequestBody PessoaPutRequest pessoaPutRequest) {
        pessoaService.atualizar(pessoaPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Pessoa> delete(@PathVariable String login) {
    pessoaService.delete(login);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
