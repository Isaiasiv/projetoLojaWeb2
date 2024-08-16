package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.service.CarrinhoService;
import com.loja.projetolojaweb2.service.EnderecoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("api/v1/carrinhos")
@RequiredArgsConstructor
@Log4j2
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @GetMapping(path = "/{id}")
    public Carrinho findById(@PathVariable Long id) {
        return carrinhoService.encontrarPorIdOuLancarExcecao(id);
    }


    @GetMapping()
    public ResponseEntity<List<Carrinho>> list() {
        return ResponseEntity.ok(carrinhoService.encontrarTodos());
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody CarrinhoPutRequest carrinhoPutRequest) {
        carrinhoService.atualizar(carrinhoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
