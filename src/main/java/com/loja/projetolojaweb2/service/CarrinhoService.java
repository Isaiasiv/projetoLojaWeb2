package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.Exceptions.CarrinhoNotFoundException;
import com.loja.projetolojaweb2.Exceptions.ProdutoNotFoundException;
import com.loja.projetolojaweb2.domain.Carrinho;
import com.loja.projetolojaweb2.domain.CarrinhoProduto;
import com.loja.projetolojaweb2.domain.Produto;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoDto;
import com.loja.projetolojaweb2.dto.carrinhoDto.CarrinhoPutRequest;
import com.loja.projetolojaweb2.dto.produtoCarrinhoDto.ProdutoCarrinhoDto;
import com.loja.projetolojaweb2.dto.produtoDto.ProdutoDetalhesDto;
import com.loja.projetolojaweb2.mapper.CarrinhoMapper;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;
import com.loja.projetolojaweb2.repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.CarrinhoInterface;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

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

//    public CarrinhoDto getCarrinhoDetalhes(Long carrinhoId){
//        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
//                .orElseThrow(() -> new CarrinhoNotFoundException("Não foi possivel encontrar o carrinho com o id:" + carrinhoId));
//        CarrinhoDto carrinhoDto = new CarrinhoDto();
//        carrinhoDto.setCarrinhoId(carrinho.getId());
//        carrinhoDto.setValorTotal(carrinho.getValorTotal());
//        carrinhoDto.setCategoria(carrinho.getCategoria());
//
//        List<ProdutoDetalhesDto> produtoDetalhesDtos = carrinho.getProdutos().stream()
//                .map(carrinhoProduto -> {
//                    Produto produto = carrinhoProduto.getProduto();
//                    ProdutoDetalhesDto produtoDto = new ProdutoDetalhesDto();
//                    produtoDto.setProdutoId(produto.getProdutoID());
//                    produtoDto.setNome(produto.getNome());
//                    produtoDto.setTipo(produto.getTipo());
//                    produtoDto.setTamanho(produto.getTamanho());
//                    produtoDto.setCor(produto.getCor());
//                    produtoDto.setImagem(produto.getImagem());
//                    produtoDto.setFabricante(produto.getFabricante());
//                    produtoDto.setCategoria(produto.getCategoria());
//                    produtoDto.setSubcategoria(produto.getSubcategoria());
//                    produtoDto.setDescricao(produto.getDescricao());
//                    produtoDto.setValor(produto.getValor());
//                    produtoDto.setQuantidade(carrinhoProduto.getQuantidade()); // Quantidade no carrinho
//                    return produtoDto;
//                }).collect(Collectors.toList());
//
//        carrinhoDto.setProdutosDetalhe(produtoDetalhesDtos);
//        return carrinhoDto;
//
//    }

    public Carrinho addProdutoToCarrinho(ProdutoCarrinhoDto produtoCarrinhoDto) {

        Carrinho carrinho = carrinhoRepository.findById(produtoCarrinhoDto.getIdCarrinho())
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho nao encontrado com id:" + produtoCarrinhoDto.getIdCarrinho() ));

        Produto produto = produtoRepository.findById(produtoCarrinhoDto.getIdProduto())
                .orElseThrow(() -> new ProdutoNotFoundException("produto não encontrado com id:" + produtoCarrinhoDto.getIdProduto() ));

       // int quantidade = produtoCarrinhoDto.getQuantidade();

        carrinho.addProduto(produto, produtoCarrinhoDto.getQuantidade());
        carrinho.setValorTotal(carrinho.getValorTotal().add(valorProduto(produto).setScale(2, RoundingMode.HALF_UP)));

      return  carrinhoRepository.save(carrinho);

    }

    @Transactional
    public Carrinho CarrinhoRemoveProdutoToCarrinho(Long idCarrinho,Long idProduto){
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho nao encontrado com id:" + idCarrinho));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ProdutoNotFoundException("produto não encontrado com id:" + idProduto));

//        if(!carrinho.getProdutos().isEmpty()){
//            throw new ProdutoNotFoundException("Produto não encontrado com id:"+ idProduto);
//        }

        CarrinhoProduto carrinhoProduto = carrinho.getProdutos().stream()
                        .filter(cp -> cp.getProduto().equals(produto))
                                .findFirst().orElseThrow(() -> new ProdutoNotFoundException("Produto nao encontrado no carrinho"));


        carrinho.setValorTotal(carrinho.getValorTotal().subtract(valorProduto(produto)).setScale(2, RoundingMode.HALF_UP));

        carrinho.getProdutos().remove(carrinhoProduto);

        return carrinhoRepository.save(carrinho);
    }

    @Transactional
    public Carrinho removerProdutoQntdToCarrinho(Long idCarrinho,Long idProduto, int quantidade){
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new CarrinhoNotFoundException("Carrinho nao encontrado com id:" + idCarrinho));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ProdutoNotFoundException("produto não encontrado com id:" + idProduto));



       // ProdutoCarrinhoDto produtoCarrinhoDto = new ProdutoCarrinhoDto();
       // produtoCarrinhoDto.setIdProduto(produto.getProdutoID());
        //produtoCarrinhoDto.setIdCarrinho(carrinho.getId());
        CarrinhoProduto carrinhoProduto = carrinho.getProdutos().stream()
                .filter(cp -> cp.getProduto().equals(produto))
                .findFirst().orElseThrow(() -> new ProdutoNotFoundException("Produto nao encontrado no carrinho"));


        if(quantidade >= carrinhoProduto.getQuantidade()){
            carrinho.setValorTotal(carrinho.getValorTotal().subtract(valorProduto(produto).multiply(BigDecimal.valueOf(carrinhoProduto.getQuantidade()))).setScale(2, RoundingMode.HALF_UP));
            carrinho.getProdutos().remove(carrinhoProduto);
        }else{
            carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() - quantidade);
            carrinho.setValorTotal(carrinho.getValorTotal().subtract(valorProduto(produto).multiply(BigDecimal.valueOf(quantidade))).setScale(2, RoundingMode.HALF_UP));
        }


        return carrinhoRepository.save(carrinho);
    }

    public BigDecimal valorProduto(Produto produto){
        return produto.getValor();
    }


}
