package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.Repository.ItemCarrinhoRepository;
import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.ItemCarrinho;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.dto.carrinhoDto.ItemProdutoDto;
import com.loja.projetolojaweb2.dto.carrinhoDto.ProdutosNoCarrinhoDto;
import com.loja.projetolojaweb2.mapper.CarrinhoMapper;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.CarrinhoInterface;
import jakarta.transaction.Transactional;
import lombok.Data;
import com.loja.projetolojaweb2.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Data
@RequiredArgsConstructor

public class CarrinhoService implements CarrinhoInterface {

    @Autowired
    private final CarrinhoRepository carrinhoRepository;
    @Autowired
    private final ProdutoRepository produtoRepository;
    @Autowired
    private final ItemCarrinhoRepository itemCarrinhoRepository;

    @Override
    public List<Carrinho> encontrarTodos() {
        return carrinhoRepository.findAll();
    }

    @Override
    public Carrinho encontrarPorIdOuLancarExcecao(Long id) {
        return carrinhoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST,"Carrinho nao encontrado"));
    }

    @Override
    public void atualizar(CarrinhoPutRequest carrinhoPutRequest) {
        Carrinho carrinhoSalvo = encontrarPorIdOuLancarExcecao(carrinhoPutRequest.getId());
        Carrinho carrinho = CarrinhoMapper.INSTANCE.toCarrinho(carrinhoPutRequest);
        carrinho.setId(carrinhoSalvo.getId());
        carrinhoRepository.save(carrinho);
    }

    public Carrinho addProdutoToCarrinho(ItemProdutoDto itemProdutoDto) {
        // Encontrar o carrinho pelo ID
        Carrinho carrinho = carrinhoRepository.findById(itemProdutoDto.getCarrinhoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado"));

        // Encontrar o produto pelo ID
        Produto produto = produtoRepository.findById(itemProdutoDto.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

       ItemCarrinho itemCarrinho = new ItemCarrinho();

       itemCarrinho.setCarrinho(carrinho);
       itemCarrinho.setProduto(produto);
       itemCarrinho.setQuantidade(itemProdutoDto.getQuantidade());

        if(carrinho.getValorTotal() == null){
            carrinho.setValorTotal(BigDecimal.ZERO);
        }
       carrinho.setValorTotal(carrinho.getValorTotal().add(produto.getValor()).multiply(new BigDecimal (itemProdutoDto.getQuantidade()).setScale(2,RoundingMode.HALF_UP)));

       itemCarrinhoRepository.save(itemCarrinho);


       // carrinho.addProduto(produto, itemProdutoDto.getQuantidade());




        return carrinhoRepository.save(carrinho);
    }

        @Transactional
        public Carrinho removerItemCarrinho(Long idCarrinho, Long idProduto) {
            Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho não encontrado"));

            // Encontrar o produto pelo ID
            Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

            ItemCarrinho itemCarrinho = carrinho.getItemCarrinho().stream()
                    .filter(cp -> cp.getProduto().equals(produto))
                    .findFirst().orElseThrow( () ->new ResponseStatusException(BAD_REQUEST,"Produto ou carrinho nao encontrado"));

            carrinho.getItemCarrinho().remove(itemCarrinho);

            return carrinhoRepository.save(carrinho);
        }

        public Carrinho removerItemQntdCarrinho(Long idCarrinho, Long idProduto, int quantidade) {

        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Carrinho nao encontrado"));

        Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

            ItemCarrinho itemCarrinho = carrinho.getItemCarrinho().stream()
                    .filter(cp -> cp.getProduto().equals(produto))
                    .findFirst().orElseThrow( () ->new ResponseStatusException(BAD_REQUEST,"Produto ou carrinho nao encontrado"));

            if(quantidade >= itemCarrinho.getQuantidade()){

                carrinho.setValorTotal(carrinho.getValorTotal().subtract(valorProduto(produto)
                                                                .multiply(BigDecimal.valueOf(quantidade)))
                                                                .setScale(2,RoundingMode.HALF_UP));
                carrinho.getItemCarrinho().remove(itemCarrinho);
            }else{
                itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() - quantidade);
                carrinho.setValorTotal(carrinho.getValorTotal().subtract(valorProduto(produto)
                                                                .multiply(BigDecimal.valueOf(quantidade)))
                                                                .setScale(2, RoundingMode.HALF_UP));

            }
            return carrinhoRepository.save(carrinho);
        }

    public List<ProdutosNoCarrinhoDto> getProdutosDoCarrinho(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow();
        return carrinho.getItemCarrinho().stream()
                .map(cp -> new ProdutosNoCarrinhoDto(cp.getProduto().getNome(),cp.getProduto().getDescricao(),cp.getProduto().getValor()))
                .collect(Collectors.toList());
    }

    private BigDecimal atualizarValorTotal(Carrinho carrinho) {
        // Calcula o valor total com base nos itens do carrinho
        return carrinho.getItemCarrinho().stream()
                .map(item -> item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal valorProduto(Produto produto){
        return produto.getValor();
    }

}

