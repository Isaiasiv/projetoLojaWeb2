package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.Repository.ItemPedidoRepository;
import com.loja.projetolojaweb2.domain.*;
import com.loja.projetolojaweb2.dto.itemPedidoDto.ItemPedidoDto;
import com.loja.projetolojaweb2.dto.itemPedidoDto.ProdutoItemPedidoDto;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPostRequest;
import com.loja.projetolojaweb2.dto.pedidoDto.PedidoPutRequest;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.mapper.EnderecoMapper;
import com.loja.projetolojaweb2.mapper.PedidoMapper;
import com.loja.projetolojaweb2.repository.PedidoRepository;
import com.loja.projetolojaweb2.repository.ProdutoRepository;
import com.loja.projetolojaweb2.service.serviceInterface.PedidoServiceInterface;
import com.loja.projetolojaweb2.util.DataAutomatica;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.loja.projetolojaweb2.repository.CarrinhoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class PedidoService implements PedidoServiceInterface {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

//    public Pedido addProdutoToPedido(ProdutoToPedidoDto produtoToPedidoDto) {
//        Optional<Produto> produtoOptional = produtoRepository.findById(produtoToPedidoDto.getIdProduto());
//        Optional<Pedido> pedidoOptional = pedidoRepository.findById(produtoToPedidoDto.getIdPedido());
//
//        if (produtoOptional.isPresent() && pedidoOptional.isPresent()) {
//            Produto produto = produtoOptional.get();
//            Pedido pedido = pedidoOptional.get();
//            pedido.addProduto(produto);
//            produtoOptional.get().setQuantidade(produto.getQuantidade() - 1);
//            return pedidoRepository.save(pedido);
//        }
//
//        throw new RuntimeException("Pedido ou Produto não encontrado");
//
//    }

    @Override
    public List<Pedido> encontrarTodos() {

        return pedidoRepository.findAll();
    }

    @Override
    public Pedido encontrarPorIdOuLancarExcecao(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(BAD_REQUEST,"Pedido não encontrado"));
    }

    @Override
    public void atualizar(PedidoPutRequest pedidoPutRequest) {
        Pedido pedidoSalvo = encontrarPorIdOuLancarExcecao(pedidoPutRequest.getId());
        Pedido pedido = PedidoMapper.INSTANCE.toPedido(pedidoPutRequest);
        pedido.setId(pedidoSalvo.getId());
        pedidoRepository.save(pedido);

    }

    @Override
    public Pedido salvar(PedidoPostRequest pedidoPostRequest) {


        LocalDateTime dataAtual = LocalDateTime.now();
        pedidoPostRequest.setData(dataAtual);

        return pedidoRepository.save(PedidoMapper.INSTANCE.toPedido(pedidoPostRequest));
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.delete(encontrarPorIdOuLancarExcecao(id));

    }

    @Override
    public Pedido criarPedido(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("Usuario logado") == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não está logado :(");
        }

        Usuario pessoaLogada = (Usuario) session.getAttribute("Usuario logado");

        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findByPessoa(pessoaLogada);
        if (!carrinhoOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não encontrado para o usuário logado");
        }

        Carrinho carrinho = carrinhoOptional.get();
        List<ItemCarrinho> itensCarrinho = carrinho.getItemCarrinho();  // Assumindo que Carrinho tem getItensCarrinho()

        if (itensCarrinho.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carrinho está vazio");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(pessoaLogada);
        pedido.setData(LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);

        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedidoRepository.save(itemPedido);

            // Ajuste opcional no estoque do produto
            Produto produto = itemCarrinho.getProduto();
            produto.setQuantidade(produto.getQuantidade() - itemPedido.getQuantidade());
        }

        // Limpar o carrinho após a criação do pedido, se necessário
        carrinho.getItemCarrinho().clear();
        carrinhoRepository.save(carrinho);

        return pedido;
    }

    private ItemPedidoDto convertItemPedidoToDto(ItemPedido itemPedido) {
        ItemPedidoDto itemPedidoDto = new ItemPedidoDto();
        itemPedidoDto.setId(itemPedido.getId());
        itemPedidoDto.setQuantidade(itemPedido.getQuantidade());

        ProdutoItemPedidoDto produtoDto = new ProdutoItemPedidoDto();
        produtoDto.setId(itemPedido.getProduto().getProdutoID());
        produtoDto.setNome(itemPedido.getProduto().getNome());
        produtoDto.setQuantidade(itemPedido.getProduto().getQuantidade());

        itemPedidoDto.setProduto(produtoDto);

        return itemPedidoDto;
    }

}
