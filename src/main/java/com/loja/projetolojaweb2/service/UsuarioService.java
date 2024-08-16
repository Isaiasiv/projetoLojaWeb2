package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.*;
import com.loja.projetolojaweb2.dto.peditoToUsuarioDto.PedidoToUsuarioDto;
import com.loja.projetolojaweb2.dto.produtoToPedidoDto.ProdutoToPedidoDto;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.mapper.CarrinhoMapper;
import com.loja.projetolojaweb2.mapper.PessoaMapper;
import com.loja.projetolojaweb2.mapper.UsuarioMapper;
import com.loja.projetolojaweb2.repository.PedidoRepository;
import com.loja.projetolojaweb2.repository.UsuarioRepository;
import com.loja.projetolojaweb2.service.serviceInterface.UsuarioInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Usuario> encontrarTodos() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario encontrarPorIdOuLancarExcecao(String login) {
        return usuarioRepository.findById(login)
                .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "Usuario não encontrada"));
    }

    @Override
    public void atualizar(UsuarioPutRequest usuarioPutRequest) {
        Usuario usuarioSalvo = encontrarPorIdOuLancarExcecao(usuarioPutRequest.getLogin());
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioPutRequest);


        Carrinho carrinhoExistente = usuarioSalvo.getCarrinho();
        if (carrinhoExistente != null) {
            usuario.setCarrinho(carrinhoExistente);
        }

        usuario.setLogin(usuarioPutRequest.getLogin());
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario salvar(UsuarioPostRequest usuarioPostRequest) {
        Carrinho carrinho = new Carrinho();
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioPostRequest);

        carrinho.setPessoa(usuario);
        usuario.setCarrinho(carrinho);

        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(String login) {
        usuarioRepository.delete(encontrarPorIdOuLancarExcecao(login));
    }

    public Usuario addPedidoToUsuario(PedidoToUsuarioDto pedidoToUsuarioDto) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(pedidoToUsuarioDto.getLoginUsuario());
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoToUsuarioDto.getPedidoId());

        if (usuarioOptional.isPresent() && pedidoOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Pedido pedido = pedidoOptional.get();
            usuario.addPedido(pedido);
            return usuarioRepository.save(usuario);
        }
        throw new RuntimeException("Usuario ou Pedido não encontrado");
    }
}

