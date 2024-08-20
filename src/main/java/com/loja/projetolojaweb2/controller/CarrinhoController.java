package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.ItemProdutoDto;
import com.loja.projetolojaweb2.dto.carrinhoDto.ProdutosNoCarrinhoDto;
import com.loja.projetolojaweb2.service.CarrinhoService;
import com.loja.projetolojaweb2.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "find by id", description = " encontra um carrinho por id",tags = "carrinho")
    @GetMapping(path = "/{id}")
    public Carrinho findById(@PathVariable Long id) {
        return carrinhoService.encontrarPorIdOuLancarExcecao(id);
    }

    @Operation(summary = "lista", description = "lista todos os carrinhos",tags = "carrinho")
    @GetMapping()
    public ResponseEntity<List<Carrinho>> list() {
        return ResponseEntity.ok(carrinhoService.encontrarTodos());
    }

    @Operation(summary = "atualizar", description = " atualiza um carrinho",tags = "carrinho")
    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody CarrinhoPutRequest carrinhoPutRequest) {
        carrinhoService.atualizar(carrinhoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "adicionar produto", description = " adiciona um produto a um carrinho (nao funciona mais)",tags = "carrinho")
    @PutMapping("/addProdutos")
    public ResponseEntity<Carrinho> addProdutoToCarrinho(@RequestBody ItemProdutoDto itemProdutoDto){
        carrinhoService.addProdutoToCarrinho(itemProdutoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @Operation(summary = "adicionar produto", description = " adiciona um produto a um carrinho especifico",tags = "carrinho")
    @PostMapping("/addProdutos/v2")
    public void adicionarProdutoAoCarrinho(@RequestBody ItemProdutoDto itemProdutoDto) {
        carrinhoService.addProdutoToCarrinho(itemProdutoDto);
    }
    @Operation(summary = "encontrar produtos", description = "encontra os produtos inseridos dentro de um carrinho",tags = "carrinho")
    @GetMapping("/{idCarrinho}/produtos")
    public List<ProdutosNoCarrinhoDto> getProdutosDoCarrinho(@PathVariable Long idCarrinho) {
        return carrinhoService.getProdutosDoCarrinho(idCarrinho);
    }
    @Operation(summary = "remover produto", description = "deleta um produto do carrinho",tags = "carrinho")
    @DeleteMapping("/deletar/produto/{idCarrinho}/{idProduto}")
    public ResponseEntity<Carrinho> removerProduto(@PathVariable Long idCarrinho,
                                                   @PathVariable Long idProduto) {
    carrinhoService.removerItemCarrinho(idCarrinho, idProduto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "remover quantidade de produto", description = "remove uma quantidade de produtos do seu carrinho caso tenha mais de um do mesmo tipo",tags = "carrinho")
    @DeleteMapping("/deletar/produto/{idCarrinho}/{idProduto}/quantidade/{quantidade}")
    public ResponseEntity<Carrinho> removerProdutoQntd(@PathVariable Long idCarrinho,
                                                       @PathVariable Long idProduto,
                                                       @PathVariable int quantidade) {
        carrinhoService.removerItemQntdCarrinho(idCarrinho, idProduto, quantidade);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
