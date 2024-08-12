package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.domain.Pessoa;
import com.loja.projetolojaweb2.domain.Usuario;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPostRequest;
import com.loja.projetolojaweb2.dto.usuarioDto.UsuarioPutRequest;
import com.loja.projetolojaweb2.mapper.PessoaMapper;
import com.loja.projetolojaweb2.mapper.UsuarioMapper;
import com.loja.projetolojaweb2.repository.UsuarioRepository;
import com.loja.projetolojaweb2.service.serviceInterface.UsuarioInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioInterface {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> encontrarTodos() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario encontrarPorIdOuLancarExcecao(String login) {
        return usuarioRepository.findById(login)
                .orElseThrow(() ->new ResponseStatusException(BAD_REQUEST,"Usuario não encontrada"));
    }

    @Override
    public void atualizar(UsuarioPutRequest usuarioPutRequest) {
        Usuario usuarioSalvo = encontrarPorIdOuLancarExcecao(usuarioPutRequest.getLogin());
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioPutRequest);
        usuario.setLogin(usuarioSalvo.getLogin());
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario salvar(UsuarioPostRequest usuarioPostRequest) {
        return usuarioRepository.save(UsuarioMapper.INSTANCE.toUsuario(usuarioPostRequest));
    }

    @Override
    public void delete(String login) {
        usuarioRepository.delete(encontrarPorIdOuLancarExcecao(login));
    }
}
