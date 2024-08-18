package com.loja.projetolojaweb2.controller;

import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.Endereco;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPostRequest;
import com.loja.projetolojaweb2.dto.EnderecoDto.EnderecoPutRequest;
import com.loja.projetolojaweb2.dto.EnderecoToPessoaDto.EnderecoToPessoaPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoDto;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.dto.produtoCarrinhoDto.ProdutoCarrinhoDto;
import com.loja.projetolojaweb2.service.CarrinhoService;
import com.loja.projetolojaweb2.service.EnderecoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final CarrinhoService carrinhoService;

    @GetMapping(path = "/{id}")
    public Carrinho findById(@PathVariable Long id) {
        return carrinhoService.encontrarPorIdOuLancarExcecao(id);
    }

//    @GetMapping("/detalhesCarrinho/{id}")
//    public ResponseEntity<CarrinhoDto> detalhesCarrinho(@PathVariable Long id) {
//        carrinhoService.getCarrinhoDetalhes(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping()
    public ResponseEntity<List<Carrinho>> list() {
        return ResponseEntity.ok(carrinhoService.encontrarTodos());
    }

    @PutMapping()
    public ResponseEntity<Void> replace(@RequestBody CarrinhoPutRequest carrinhoPutRequest) {
        carrinhoService.atualizar(carrinhoPutRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/addProduto")
    public ResponseEntity<Carrinho> addProdutoToCarrinho(@RequestBody ProdutoCarrinhoDto produtoCarrinhoDto) {
        Carrinho carrinho = carrinhoService.addProdutoToCarrinho(produtoCarrinhoDto);
        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/removerProduto/{idCarrinho}/{idProduto}")
    public ResponseEntity<Carrinho> removeProdutoToCarrinho(@PathVariable Long idCarrinho,
                                                            @PathVariable Long idProduto) {
        Carrinho carrinho = carrinhoService.CarrinhoRemoveProdutoToCarrinho(idCarrinho, idProduto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/removerProdutoQntd/{idCarrinho}/{idProduto}/{quantidade}")
    public ResponseEntity<Carrinho> removerProdutoQntdToCarrinho(@PathVariable Long idCarrinho,
                                                                 @PathVariable Long idProduto,
                                                                 @PathVariable int quantidade) {
        Carrinho carrinho = carrinhoService.removerProdutoQntdToCarrinho(idCarrinho, idProduto,quantidade);
        return ResponseEntity.noContent().build();

    }
}


