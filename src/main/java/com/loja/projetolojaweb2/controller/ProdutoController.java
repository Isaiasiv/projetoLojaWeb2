package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Produto;

import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/produtos")
@Log4j2
@RequiredArgsConstructor
public class ProdutoController {
    @Autowired
    private final ProdutoService produtoService;

    @GetMapping("/produto")
    public String produto() {
        return "teste";
    }

    @PostMapping( "/create")
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

    @GetMapping("/nome/{nome}")
    public List<Produto> nome(@PathVariable("nome") String nome) {
        return produtoService.findByNome(nome);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Produto> tipo(@PathVariable("tipo") String tipo) {
        return produtoService.findByTipo(tipo);
    }

    @GetMapping("/tamanho/{tamanho}")
    public List<Produto> tamanho(@PathVariable("tamanho") String tamanho) {
        return produtoService.findByTamanho(tamanho);
    }

    @GetMapping("/cor/{cor}")
    public List<Produto> cor(@PathVariable("cor") String cor) {
        return produtoService.findByCor(cor);
    }

    @GetMapping("/fabricante/{fabricante}")
    public List<Produto> fabricante(@PathVariable("fabricante") String fabricante) {
        return produtoService.findByFabricante(fabricante);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Produto> categoria(@PathVariable("categoria") String categoria) {
        return produtoService.findByCategoria(categoria);
    }

    @GetMapping("/subcategoria/{subcategoria}")
    public List<Produto> subcategoria(@PathVariable("subcategoria") String subcategoria) {
        return produtoService.findBySubcategoria(subcategoria);
    }

    @GetMapping("/valor/{valor}")
    public List<Produto> valor(@PathVariable("valor") double valor) {
        return produtoService.findByValor(valor);
    }

    @GetMapping("/quantidade/{quantidade}")
    public List<Produto> quantidade(@PathVariable("quantidade") int quantidade) {
        return produtoService.findByQuantidade(quantidade);
    }
}
