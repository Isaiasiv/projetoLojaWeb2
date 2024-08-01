package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produto")
    public String produto() {
        return "teste";
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

    @GetMapping("/imagem/{imagem}")
    public List<Produto> imagem(@PathVariable("imagem") String imagem) {
        return produtoService.findByImagem(imagem);
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

    @GetMapping("/descricao/{descricao}")
    public List<Produto> descricao(@PathVariable("descricao") String descricao) {
        return produtoService.findByDescricao(descricao);
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
