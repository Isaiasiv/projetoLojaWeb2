package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPostRequest;
import com.loja.projetolojaweb2.dto.pessoaDto.PessoaPutRequest;
import com.loja.projetolojaweb2.service.PessoaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
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

    @PutMapping("/addEndereco")
    public ResponseEntity<Endereco> addEnderecoToPessoa(@RequestBody EnderecoToPessoaPutRequest enderecoToPessoaPutRequest) {
        Endereco endereco = pessoaService.addEnderecoToPessoa(enderecoToPessoaPutRequest);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping(path = "/{login}")
    public ResponseEntity<Pessoa> delete(@PathVariable String login) {
    pessoaService.delete(login);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping(path = "/login/{login}/{senha}")
//    public ResponseEntity<Pessoa> login(@PathVariable String login,
//                                        @PathVariable String senha) {
//        try{
//            pessoaService.fazerLogin(login,senha);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception ex){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login ou senhas incorretos");
//        }
//
//
//    }


}
