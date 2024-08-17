package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPostRequest;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoPutRequest;
import com.loja.projetolojaweb2.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Teste", description = " metodo teste",tags = "Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "produto salvo com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao salva produto")
    })
    @GetMapping("/produto")
    public String produto() {
        return "teste";
    }

    @Operation(summary = "Cadastrar produto",description = " cria novo produto \n " +
            "Categorias aceitas: Roupa,calçado,mesa,banho\n " +
            "Subcategoria: masculino, feminino e unisex\n " +
            "Tipo:camisa,short,calça,tenis e etc.\n ",tags = "Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "produto salvo com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao salva produto")
    })
    @PostMapping( "/create")
    public ResponseEntity<Produto> save(@RequestBody ProdutoPostRequest produtoPostRequest) {
        return new ResponseEntity<>(produtoService.salvar(produtoPostRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Lista de produto",description = "busca todos produtos cadastrados",tags = "Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "produtos encontrados"),
            @ApiResponse(responseCode = "400",description = "não foi encontrado nenhum produto")
    })
    @GetMapping()
    public ResponseEntity<List<Produto>> list() {
        return ResponseEntity.ok(produtoService.listAll());
    }

    @Operation(summary = "Atualiza produto",description = "Atualiza dados de um produto atraves do ID",tags = "Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao Atualiza produto")
    })
    @PutMapping()
    public ResponseEntity<Produto> replace(@RequestBody ProdutoPutRequest produtoPutRequest) {
        produtoService.atualizar(produtoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Exluir produto",description = "Exclui um produto atraves do ID,",tags = "Produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "produto excluido com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro ao excluir produto")
    })
    @DeleteMapping("/{produtoID}")
    public ResponseEntity<Void> delete(@PathVariable String produtoID) {
        produtoService.delete(produtoID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
