package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
@Log4j2
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/produto")
    public String produto() {
        return "teste";
    }

    @PostMapping()
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequest produtoPostRequest) {
        Produto savedProduto = produtoService.salvar(produtoPostRequest);
        return new ResponseEntity<>(savedProduto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> list() {
        return ResponseEntity.ok(produtoService.listAll());
    }

    @PutMapping()
    public ResponseEntity<Produto> replace(@RequestBody ProdutoPutRequest produtoPutRequest) {
        produtoService.atualizar(produtoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{produtoID}")
    public ResponseEntity<Void> delete(@PathVariable String produtoID) {
        produtoService.delete(produtoID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
